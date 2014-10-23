/**
	 * ����
	 * @param city
	 */
	private void SoapTest(String city){
		//ָ�������ռ�
		String NAME_SPACE = "http://WebXml.com.cn";
		//�����ӿڵ�ַ
		String URL = "http://www.webxml.com.cn/webservices/weatherwebservices.asmx";
		//���÷�����
		String METHOD_NAME = "getWeatherbyCityName";
		//���ò�ѯ�ӿڲ���
		String SOAP_ACTION = "http://WebXml.com.cn/getWeatherbyCityName";
		
		SoapObject detail;
		
		SoapObject rpc = new SoapObject(NAME_SPACE, METHOD_NAME);
		//��SoapObject�����������
		rpc.addProperty("theCityName", city);
		//����HttpTransportSE���󣬲�ָ��WebService��WSDL�ĵ���URL
		HttpTransportSE ht = new HttpTransportSE(URL);
		//����debugģʽ
		ht.debug = true;
		//������л���envelope
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		//����bodyOut���Ե�ֵΪSoapObject����rpc
		envelope.bodyOut = rpc;
		//ָ��webservices������ΪdotNet
		envelope.dotNet = true;
		envelope.setOutputSoapObject(rpc);
		//ʹ��call��������WebServices����
		try {
			ht.call(SOAP_ACTION, envelope);
		} catch (HttpResponseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		//��ȡ���صĽ��
		SoapObject result = (SoapObject) envelope.bodyIn;
		//ʹ��getResponse�������WebService�����ķ��ؽ��
		detail = (SoapObject) result.getProperty("getWeatherbyCityNameResult");
		System.out.println("detail"+detail);
		//�������ص�������ϢΪSoapObject���󣬶�����н���
		try {
			parseWeather(detail);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * ����
	 * @param detail
	 */
	private void parseWeather(SoapObject detail) throws UnsupportedEncodingException{
		//��ȡ����
		String date = detail.getProperty(6).toString();
		//��ȡ������Ϣ
		String weatherToday = "���죺"+date.split("")[0];
		weatherToday = weatherToday+"������"+date.split("")[1];
		weatherToday = weatherToday+"���£�"+detail.getProperty(5).toString();
		weatherToday = weatherToday+"������"+detail.getProperty(7).toString();
		System.out.println("weatherToday = "+weatherToday);
	}