�Ĵ�����IMЭ��
XMPP������չ��Ϣ�����Э��
IMPP����ʱ��Ϣ�Ϳռ�Э��
PRIM���ռ�ͼ�ʱ��ϢЭ��
SIP����Լ�ʱͨѶ�Ϳռ�ƽ������Ľ��̿�ʼЭ��

XMPP��չЭ��Jingle֧��������Ƶ

Asmack��������飺

1��ConnectionConfiguration��ͨ����������������XMPP���������ӵ����á��������������Ƿ�ʹ��TLS��SASL���ܵȡ�������ConnectionConfiguration.SecurityMode��Ƕ�ࡣ

2��XMPPConnection���������������XMPP���񡣿���ʹ��connect()��������������������ӣ�ʹ��disconnect()�����Ͽ�������������ӡ�
�ڴ�������ǰ��������XMPPConnection.DEBUG_ENABLEDΪtrue��ʹ���������п��Ե���һ��GUI���ڣ�������ʾ�����뷢��Packet��Ϣ��

3��ChatManager�����ڼ�ص�ǰ����chat������ʹ��createChat(String userJID,Message Listener listener)����һ�����졣

4��Chat��Chat���ڼ�������û����һϵ����Ϣ��message����ʹ��addMessageListener(MessageListener listener)�������κ���Ϣ����ʱ���ᴥ��listener��processMessage(Chat chat,Message message)������

5��Message��Message���ڱ�ʾһ����Ϣ���������õ��Թ��߿������Ͱ��ͽ��հ��ľ������ݣ���
Message�������ڲ��ࣺһ����Message.Body����ʾ��Ϣ�壻��һ����Message.Type����ʾ��Ϣ���͡�

6��Roster�������ʾ�洢��һ�������ᣬ���а����ܶ�RosterEntry��Ϊ�����ڹ��������������䵽��group�С���������XMPP��������Ӻ����ʹ��connection.getRoster()��ȡRoster����
����û�����ʹ��һ�����������൱��QQ�Ӻ��ѣ����Զ���Ŀ���û�������ʹ��ö������Roster.SubscriptionMode��ֵ������Щ����accept_all��ʾ�������ж�������reject_all��ʾ�ܾ����ж�������manual��ʾ�ֹ�����������
�����鷽����
RosterGroup group = roster.createGroup("��ѧ");
�������������RosterEntry���󣬷������£�
group.addEntry("entry");

7��RosterEntry��RosterEntry��ʾRoster�������ᣩ�е�ÿ����¼�����������û���JID���û������û�������ǳơ�

8��RosterGroup��RosterGroup��ʾRosterEntry���顣����ʹ��addEntry(RosterEntry entry)��ӣ�contains(String user)�ж�ĳ�û��Ƿ������У�removeEntry(RosterEntry entry)�Ǵ������Ƴ���getEntries()��ȡ����RosterEntry��

9��Presence��Presence��ʾXMPP״̬��packet��ÿ��presence packet����һ��״̬����ö������Presence.Type��ֵ��ʾ