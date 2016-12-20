package com.mina.test4;

  
import org.apache.mina.core.session.IoSession;

import sun.net.www.http.HttpClient;
  
public class Execute {  
    private IoSession ioSession;  
    private Header header;  
  
    public Execute(IoSession ioSession, Header header) {  
        this.ioSession = ioSession;  
        this.header = header;  
    }  
  
    public void handler() {  
        if (null != header.getId() && !"".equals(header.getId())) {  
            IoSession session = SessionMap.getSession(header.getId());  
            if (null != session) {  
                if (!session.equals(ioSession)) {  
                    SessionMap.unregisterSession(header.getId());  
                    SessionMap.registerSession((LoginBean) session  
                            .getAttribute("USERNAME"), ioSession);  
                }  
            }  
        }  
        if ((null != header.getUsername() && !"".equals(header.getUsername()))) {  
            IoSession session = SessionMap.getSession(header.getUsername());  
            if (null != session) {  
                if (!session.equals(ioSession)) {  
                    SessionMap.unregisterSession(header.getUsername());  
                    SessionMap.registerSession((LoginBean) session  
                            .getAttribute("USERNAME"), ioSession);  
                }  
            }  
        }  
        System.out.println("---------------------" + header.getType()  
                + "  Systemtime:" + System.currentTimeMillis());  
        // 登陆请求  
        /**
         * if ("login".equals(header.getType())) {  
            LoginHandler.execute(ioSession, header);  
            // 单聊  
        } else if ("single".equals(header.getType())) {  
            SingleHandler.execute(ioSession, header);  
            // 群聊  
        } else if ("group".equals(header.getType())) {  
            GroupHandler.execute(ioSession, header);  
            // 客户端离线消息删除请求  
        } else if ("offlinemessagereceive".equals(header.getType())) {  
            DelOffLineMessageHandler.execute(ioSession, header);  
            // 消息转发  
        } else if ("forward".equals(header.getType())) {  
            ForwardMessageHandler.execute(ioSession, header);  
            // 获取离线消息  
        } else if ("offline".equals(header.getType())) {  
            FindOfflLineMessageHandler.execute(ioSession, header);  
            // 获取指定人聊天记录  
        } else if ("findsinglemessage".equals(header.getType())) {  
            FindSingleMessageHandler.execute(ioSession, header);  
            // 获取指定群组聊天记录  
        } else if ("findgroupmessage".equals(header.getType())) {  
            FindGroupMessageHandler.execute(ioSession, header);  
            // 获取离线通讯录  
        } else if ("offlineaddress".equals(header.getType())) {  
            FindOfflineAddress.execute(ioSession, header);  
        } else if ("delsingleonemsg".equals(header.getType())) {  
            // 删除一条消息，在客户端长按删除  
            DelSingleOneMessageHandler.execute(ioSession, header);  
            // 删除全部单聊消息  
        } else if ("delsingleallmsg".equals(header.getType())) {  
            DelSingleAllMessageHandler.execute(ioSession, header);  
            // 删除群组消息  
        } else if ("delgrouponemsg".equals(header.getType())) {  
            DelGroupOneMessageHandler.execute(ioSession, header);  
            // 客户端更新离线通讯录之后的回执  
        } else if ("contactserverupdate".equals(header.getType())) {  
            SontactServerUpdate.execute(ioSession, header);  
            // web服务器发送更新请求（通讯录）  
        } else if ("serverupdate".equals(header.getType())) {  
            SendMessageAllHandler.execute(header);  
            // web服务器发送更新请求（群组）  
        } else if ("serverroom".equals(header.getType())) {  
            SendGroupMessageHandler.execute(header);  
            // web发送即时公告 分享  
        } else if ("notice".equals(header.getType())) {  
            NoticeMessageHandler.execute(header);  
            // 获取新朋友，对外发布  
        } else if ("addfriend".equals(header.getType())) {  
            AddFriendHandler.execute(header);  
        } else if ("findnewfriend".equals(header.getType())) {  
            FindNewFriendHandler.execute(ioSession, header);  
        } else if ("findnewfriendreceive".equals(header.getType())) {  
            DelNewFriendHandler.execute(header);  
        } else if ("agreeAddFriend".equals(header.getType())) {  
            AgreeAddFriendHandler.execute(ioSession, header);  
            // 所有http请求都用socket  
        } else if ("url".equals(header.getType())) {  
            HttpClient.execute(ioSession, header);  
            // 心跳设置 时间长则没必要这么频繁  
        } else if ("heard".equals(header.getType())) {  
            HeardHandler.execute(ioSession, header);  
            // 下线  
        } else if ("logout".equals(header.getType())) {  
            SessionMap.unregisterSession(header.getUsername());  
            ioSession.close(true);  
            // 支付  
        } else if ("".equalsIgnoreCase(header.getType())) {  
            ReapalPushHandler.execute(header);  
        }  */
    }  
}  