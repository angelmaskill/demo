package com.mina.test2;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.mina.utils.Tools;

/**
 * <pre>
 * 测试数据:
 * 0000071789A200F90101792000000237020000017B97CD1E000F0A1808183231382E3032382E3133362E30303535003139322E3136382E3030352E3034363A98056A000508051E0C1E0A3C770002400A080A1E024C0A3C1E023C0A7828460013840671687FFFFF1B78000000003400003456303030313035463230313600BCBABE0000000000000000000001E1101E000237020A0000050000000A00C8000000000002000A3C00000000000001003C280500000000001E000AFFFF500A000000050000053231382E3032382E3133362E30323513273231382E3032382E3133362E30323513270C120500000000000000000000000000001E68
 * 000007149607001F025401FD8C20681DD3036844244297570353007354E422
 * 000007148FFC001C028401FD1AC068119C0304B374429756B80069F1 测试数据.
 * </pre>
 * 
 * @author mayanlu
 *
 */
public class MainMessageDecoder extends CumulativeProtocolDecoder {
	private Logger logger = Logger.getLogger(MainMessageDecoder.class);

	private CharsetDecoder decoder;

	public MainMessageDecoder(Charset charset) {
		this.decoder = charset.newDecoder();
	}

	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
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
		logger.info("in.remaining()" + in.remaining());
		while (in.remaining() > 0) {
			/**
			 * tag是包头标示符
			 */
			tag = "";
			/**
			 * 前8位:上传包头 第9位:包类型
			 */
			logger.info("in.remaining()" + in.remaining());
			if (in.remaining() < 11) {
				return false;
			}
			/**
			 * 取当前的position的快照标记mark
			 */
			in.mark();
			/**
			 * 读取一个字节
			 */
			short tag_a = in.get();// 上传标识第1个字节为字符串0,十六进制0,对应十进制为48
			if (tag_a == 0) {
				short tag_b = in.get();// 上传标识第2个字节
				if (tag_b == 0) {
					tag = "0000";
				} else {
					return true;
				}

			} else if (tag_a == 0x30) {// 读取四个0-0 begin
				short tag_b = in.get();
				if (tag_b == 0x30) {
					short tag_c = in.get();
					if (tag_c == 0x30) {
						short tag_d = in.get();
						if (tag_d == 0x30) {
							tag = "30303030";
						} else {
							return true;
						}
					} else {
						return true;
					}
				} else {
					return true;
				}// 读取四个0-0 end
			} else {
				return true;
			}
			logger.info("in.remaining()" + in.remaining());// 已经读取了上传标识----4个0
			if (tag.equalsIgnoreCase("30303030") && in.remaining() > 1) {
				head = "0000";
				if (in.remaining() < 12) {
					return true;
				}
				// 协议版本号-07
				protocolVer = in.getString(2, decoder);
				if (!protocolVer.equalsIgnoreCase("06")) {
					if (!protocolVer.equalsIgnoreCase("07")) {
						// logger.warn("\r\n数据不符合协议版本直接扔出");
						return true;
					}
				}
				// 车载机编号 1789A2
				machNo = in.getString(6, decoder);
				// 包长度 00F9,249
				strLength = in.getString(4, decoder);
				// 十六进制转为10进制
				try {
					len = Integer.parseInt(strLength, 16) * 2;
				} catch (NumberFormatException ex) {
					return false;
				}
				if (len > 1000) {
					return true;
				}
				logger.info("in.remaining()" + in.remaining());
				logger.info("len - 16 "+ (len - 16));
				logger.info(in.remaining() < len - 16 || len < 17);
				// 前边已经读取了16个字符,这段来进行<<分包处理>>
				if (in.remaining() < len - 16 || len < 17) {
					in.reset();//恢复position到先前标记的mark
					return false;
				} else {
					MessageModel mmodel = new MessageModel();

					if (!session.containsAttribute("machNo")) {
						session.setAttribute("machNo", Integer.parseInt(machNo, 16));
					}
					// 读取包体,除去(4位CRC校验码+4个0+2位协议号07+6位车载机编号+4位包长度)
					data = in.getString(len - 20, decoder);
					if (data.length() < 2) {
						return true;
					}
					// 包类型-02包
					protocolNo = data.substring(0, 2);
					// CRC校验码 69F1
					check = in.getString(4, decoder);
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
						out.write(mmodel);
					} else {

						logger.info("\r\nmach_no=" + Integer.parseInt(machNo, 16) + ";CRC16======校验失败:\r\nDATA:"
								+ checkData + check);
						return false;
					}
					logger.info("in.remaining()" + in.remaining());
					/**
					 * 此处代码进行<<粘包处理>>.
					 */
					if (in.remaining() > 0) {
						return true;
					}
				}
			} else if (tag.equalsIgnoreCase("0000")) {
				head = "0000";
				logger.info("in.remaining()" + in.remaining());
				if (in.remaining() < 8) {
					in.reset();
					return false;
				}
				protocolVer = Tools.turnLength(Integer.toHexString(in.get()), 2);
				if (!protocolVer.equalsIgnoreCase("06")) {
					if (!protocolVer.equalsIgnoreCase("07")) {
						logger.info("\r\n数据不符合协议版本直接扔出");
						return true;
					}
				}
				byte[] tmp = new byte[3];
				in.get(tmp);
				StringBuffer mess = new StringBuffer(Tools.bytes2Hex(tmp));
				machNo = mess.toString().toUpperCase();
				tmp = new byte[2];
				in.get(tmp);
				mess = new StringBuffer(Tools.bytes2Hex(tmp));
				strLength = mess.toString().toUpperCase();
				len = Integer.valueOf(strLength, 16);
				if (len > 1024) {
					return false;
				}
				logger.info("in.remaining()" + in.remaining());
				if ((in.remaining() < (len - 8)) || len < 9) {
					in.reset();
					return false;
				} else {
					MessageModel mmodel = new MessageModel();
					if (!session.containsAttribute("machNo")) {
						session.setAttribute("machNo", Integer.parseInt(machNo, 16));
						logger.info("add machNo to session " + Integer.parseInt(machNo, 16));
					}
					tmp = new byte[len - 8];
					in.get(tmp);
					mess = new StringBuffer(Tools.bytes2Hex(tmp));
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
						out.write(mmodel);

					} else {
						logger.info("\r\nmach_no=" + Integer.parseInt(machNo, 16) + ";CRC16======校验失败:\r\nDATA:"
								+ checkData + check);
						return false;
					}
					logger.info("in.remaining()" + in.remaining());
					if (in.remaining() > 0) {
						return true;
					}
				}
			} else {
				return false;
			}
		}
		return false;
	}
}