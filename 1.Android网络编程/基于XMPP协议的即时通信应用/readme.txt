四大主流IM协议
XMPP：可扩展消息与存在协议
IMPP：即时信息和空间协议
PRIM：空间和即时信息协议
SIP：针对即时通讯和空间平衡扩充的进程开始协议

XMPP扩展协议Jingle支持语音视频

Asmack的相关类简介：

1）ConnectionConfiguration。通过该类设置用于与XMPP服务建立连接的配置。它能配置连接是否使用TLS、SASL加密等。它包含ConnectionConfiguration.SecurityMode内嵌类。

2）XMPPConnection。这个类用来连接XMPP服务。可以使用connect()方法建立与服务器的连接，使用disconnect()方法断开与服务器的连接。
在创建连接前可以设置XMPPConnection.DEBUG_ENABLED为true，使开发过程中可以弹出一个GUI窗口，用于显示连接与发送Packet信息。

3）ChatManager。用于监控当前所有chat。可以使用createChat(String userJID,Message Listener listener)创建一个聊天。

4）Chat。Chat用于监控两个用户间的一系列消息（message）。使用addMessageListener(MessageListener listener)，当有任何消息到达时将会触发listener的processMessage(Chat chat,Message message)方法。

5）Message。Message用于表示一个消息包（可以用调试工具看到发送包和接收包的具体内容）。
Message有两个内部类：一个是Message.Body，表示消息体；另一个是Message.Type，表示消息类型。

6）Roster。该类表示存储了一个花名册，其中包含很多RosterEntry。为了易于管理，花名册的项被分配到了group中。当建立与XMPP服务的连接后可以使用connection.getRoster()获取Roster对象。
别的用户可以使用一个订阅请求（相当于QQ加好友）尝试订阅目的用户。可以使用枚举类型Roster.SubscriptionMode的值处理这些请求：accept_all表示接收所有订阅请求；reject_all表示拒绝所有订阅请求；manual表示手工处理订阅请求。
创建组方法：
RosterGroup group = roster.createGroup("大学");
可以向组中添加RosterEntry对象，方法如下：
group.addEntry("entry");

7）RosterEntry。RosterEntry表示Roster（花名册）中的每条记录。它包含了用户的JID、用户名、用户分配的昵称。

8）RosterGroup。RosterGroup表示RosterEntry的组。可以使用addEntry(RosterEntry entry)添加，contains(String user)判断某用户是否在组中，removeEntry(RosterEntry entry)是从组中移除，getEntries()获取所有RosterEntry。

9）Presence。Presence表示XMPP状态的packet。每个presence packet都有一个状态，用枚举类型Presence.Type的值表示