/**
	 * 请求
	 * @param city
	 */
	private void SoapTest(String city){
		//指定命名空间
		String NAME_SPACE = "http://WebXml.com.cn";
		//给出接口地址
		String URL = "http://www.webxml.com.cn/webservices/weatherwebservices.asmx";
		//设置方法名
		String METHOD_NAME = "getWeatherbyCityName";
		//设置查询接口参数
		String SOAP_ACTION = "http://WebXml.com.cn/getWeatherbyCityName";
		
		SoapObject detail;
		
		SoapObject rpc = new SoapObject(NAME_SPACE, METHOD_NAME);
		//给SoapObject对象添加属性
		rpc.addProperty("theCityName", city);
		//创建HttpTransportSE对象，并指定WebService的WSDL文档的URL
		HttpTransportSE ht = new HttpTransportSE(URL);
		//设置debug模式
		ht.debug = true;
		//获得序列化的envelope
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		//设置bodyOut属性的值为SoapObject对象rpc
		envelope.bodyOut = rpc;
		//指定webservices的类型为dotNet
		envelope.dotNet = true;
		envelope.setOutputSoapObject(rpc);
		//使用call方法调用WebServices方法
		try {
			ht.call(SOAP_ACTION, envelope);
		} catch (HttpResponseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		//获取返回的结果
		SoapObject result = (SoapObject) envelope.bodyIn;
		//使用getResponse方法获得WebService方法的返回结果
		detail = (SoapObject) result.getProperty("getWeatherbyCityNameResult");
		System.out.println("detail"+detail);
		//解析返回的数据信息为SoapObject对象，对其进行解析
		try {
			parseWeather(detail);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * 解析
	 * @param detail
	 */
	private void parseWeather(SoapObject detail) throws UnsupportedEncodingException{
		//获取日期
		String date = detail.getProperty(6).toString();
		//获取天气信息
		String weatherToday = "今天："+date.split("")[0];
		weatherToday = weatherToday+"天气："+date.split("")[1];
		weatherToday = weatherToday+"气温："+detail.getProperty(5).toString();
		weatherToday = weatherToday+"风力："+detail.getProperty(7).toString();
		System.out.println("weatherToday = "+weatherToday);
	}