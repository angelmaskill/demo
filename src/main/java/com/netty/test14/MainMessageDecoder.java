package com.netty.test14;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.AttributeKey;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.log4j.Logger;

import com.mina.utils.Tools;

public class MainMessageDecoder extends ByteToMessageDecoder {
	private Logger logger = Logger.getLogger(MainMessageDecoder.class);

	private Charset decoder;

	public MainMessageDecoder(Charset charset) {
		this.decoder = charset;
	}

	@Override
	protected void decode(ChannelHandlerContext session, ByteBuf in, List<Object> out) throws Exception {
		String tag;
		String protocolVer;
		String machNo = "";
		String protocolNo;
		String strLength = "";
		String data = "";
		String purData = "";
		String check = "";
		String head = "";
		int len;
		logger.info("in.readableBytes()" + in.readableBytes());
		while (in.readableBytes() > 0) {
			/**
			 * tag是包头标示符
			 */
			tag = "";
			/**
			 * 前8位:上传包头 第9位:包类型
			 */
			logger.info("in.readableBytes()" + in.readableBytes());
			if (in.readableBytes() < 11) {
				return;
			}
			/**
			 * 取当前的position的快照标记mark
			 */
			// in.mark();
			/**
			 * 读取一个字节
			 */
			short tag_a = in.getByte(1);// 上传标识第1个字节为字符串0,十六进制0,对应十进制为48
			if (tag_a == 0) {
				short tag_b = in.getByte(2);// 上传标识第2个字节
				if (tag_b == 0) {
					tag = "0000";
				} else {
					return;
				}

			} else if (tag_a == 0x30) {// 读取四个0-0 begin
				short tag_b = in.getByte(3);
				if (tag_b == 0x30) {
					short tag_c = in.getByte(4);
					if (tag_c == 0x30) {
						short tag_d = in.getByte(5);
						if (tag_d == 0x30) {
							tag = "30303030";
						} else {
							return;
						}
					} else {
						return;
					}
				} else {
					return;
				}// 读取四个0-0 end
			} else {
				return;
			}
			logger.info("in.readableBytes()" + in.readableBytes());// 已经读取了上传标识----4个0
			if (tag.equalsIgnoreCase("30303030") && in.readableBytes() > 1) {
				head = "0000";
				if (in.readableBytes() < 12) {
					return;
				}
				// 协议版本号-07
				protocolVer = new String(in.readBytes(2).array(), decoder);
				if (!protocolVer.equalsIgnoreCase("06")) {
					if (!protocolVer.equalsIgnoreCase("07")) {
						// logger.warn("\r\n数据不符合协议版本直接扔出");
						return;
					}
				}
				// 车载机编号 1789A2
				machNo = new String(in.readBytes(6).array(), decoder);
				// 包长度 00F9,249
				strLength = new String(in.readBytes(4).array(), decoder);
				// 十六进制转为10进制
				try {
					len = Integer.parseInt(strLength, 16) * 2;
				} catch (NumberFormatException ex) {
					return;
				}
				if (len > 1000) {
					return;
				}
				logger.info("in.readableBytes()" + in.readableBytes());
				logger.info("len - 16 " + (len - 16));
				logger.info(in.readableBytes() < len - 16 || len < 17);
				// 前边已经读取了16个字符,这段来进行<<分包处理>>
				if (in.readableBytes() < len - 16 || len < 17) {
					return;
				} else {
					MessageModel mmodel = new MessageModel();
					AttributeKey<Integer> machNoKey = AttributeKey.valueOf("machNo");
					if (null != session.attr(machNoKey)) {
						session.attr(machNoKey).set(Integer.parseInt(machNo, 16));
					}
					// 读取包体,除去(4位CRC校验码+4个0+2位协议号07+6位车载机编号+4位包长度)
					data = new String(in.readBytes(len - 20).array(), decoder);
					if (data.length() < 2) {
						return;
					}
					// 包类型-02包
					protocolNo = data.substring(0, 2);
					// CRC校验码 69F1
					check = new String(in.readBytes(4).array(), decoder);
					// 重新拼包,进行CRC16校验
					String checkData = head + protocolVer + machNo + strLength + data;
					// logger.warn("数据包"+protocolNo+"\r\n收到数据"+checkData+"\r\nCRC校验"+check);
					if (Tools.CRC16(checkData).equalsIgnoreCase(check)) {
						mmodel.setData(checkData + check);
						mmodel.setTag(tag);
						mmodel.setType(0);
						mmodel.setProtocolVer(Integer.parseInt(protocolVer, 16));
						mmodel.setProtocolNo(protocolNo);
						mmodel.setMachNo(Integer.parseInt(machNo, 16));
						mmodel.setLength(len);
						mmodel.setCrc(check);
						mmodel.setPurData(data.substring(2));
						out.add(mmodel);
					} else {

						logger.info("\r\nmach_no=" + Integer.parseInt(machNo, 16) + ";CRC16======校验失败:\r\nDATA:"
								+ checkData + check);
						return;
					}
					logger.info("in.readableBytes()" + in.readableBytes());
					/**
					 * 此处代码进行<<粘包处理>>.
					 */
					if (in.readableBytes() > 0) {
						return;
					}
				}
			} else if (tag.equalsIgnoreCase("0000")) {
				head = "0000";
				logger.info("in.readableBytes()" + in.readableBytes());
				if (in.readableBytes() < 8) {
					return;
				}
				protocolVer = Tools.turnLength(Integer.toHexString(in.readBytes(1).arrayOffset()), 2);
				if (!protocolVer.equalsIgnoreCase("06")) {
					if (!protocolVer.equalsIgnoreCase("07")) {
						logger.info("\r\n数据不符合协议版本直接扔出");
						return;
					}
				}
				// byte[] tmp = new byte[3];
				StringBuffer mess = new StringBuffer(Tools.bytes2Hex(in.readBytes(3).array()));
				machNo = mess.toString().toUpperCase();
				// tmp = new byte[2];
				mess = new StringBuffer(Tools.bytes2Hex(in.readBytes(2).array()));
				strLength = mess.toString().toUpperCase();
				len = Integer.valueOf(strLength, 16);
				if (len > 1024) {
					return;
				}
				logger.info("in.readableBytes()" + in.readableBytes());
				if ((in.readableBytes() < (len - 8)) || len < 9) {
					return;
				} else {
					MessageModel mmodel = new MessageModel();
					AttributeKey<Integer> machNoKey = AttributeKey.valueOf("machNo");
					if (null != session.attr(machNoKey)) {
						session.attr(machNoKey).set(Integer.parseInt(machNo, 16));
					}
					/**
					 * <pre>
					 * if (!session.containsAttribute(&quot;machNo&quot;)) {
					 * 	session.setAttribute(&quot;machNo&quot;, Integer.parseInt(machNo, 16));
					 * 	logger.info(&quot;add machNo to session &quot; + Integer.parseInt(machNo, 16));
					 * }
					 * </pre>
					 */

					mess = new StringBuffer(Tools.bytes2Hex(in.readBytes(len - 8).array()));
					data = mess.toString().toUpperCase();
					protocolNo = data.substring(0, 2);
					check = data.substring(data.length() - 4, data.length());
					purData = data.substring(2, data.length() - 4);
					String checkData = head + protocolVer + machNo + strLength + protocolNo + purData;
					logger.warn("数据包" + protocolNo + "\r\n收到数据" + checkData + "\r\nCRC校验" + check);
					if (Tools.CRC16(checkData).equalsIgnoreCase(check)) {
						mmodel.setData(checkData + check);
						mmodel.setTag(tag);
						mmodel.setType(1);
						mmodel.setProtocolVer(Integer.parseInt(protocolVer, 16));
						mmodel.setProtocolNo(protocolNo);
						mmodel.setMachNo(Integer.parseInt(machNo, 16));
						mmodel.setPurData(data.substring(2, data.length() - 4));
						out.add(mmodel);

					} else {
						logger.info("\r\nmach_no=" + Integer.parseInt(machNo, 16) + ";CRC16======校验失败:\r\nDATA:"
								+ checkData + check);
						return;
					}
					logger.info("in.readableBytes()" + in.readableBytes());
					if (in.readableBytes() > 0) {
						return;
					}
				}
			} else {
				return;
			}
		}
		return;
	}

}
