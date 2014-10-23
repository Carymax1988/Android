try {
			URL newUrl = new URL("http://www.baidu.com");
			URLConnection connection = newUrl.openConnection();
			DataInputStream dis = new DataInputStream(connection.getInputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(dis, "UTF-8"));
			String html = "";
			String readLine = null;
			while((readLine=in.readLine())!=null){
				html += readLine;
			}
			in.close();
			Log.d("Debug", html);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}