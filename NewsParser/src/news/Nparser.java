package news;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Nparser
{

	private static ArrayList<HashMap<String, String>> arraylist;
	private static ArrayList<String> sym = new ArrayList<String>();
	private static final String TITLE = "title";
	private static final String DESC = "description";
	private static final String THUMB = "thumbnail";
	private static final String url = "http://api.nytimes.com/svc/search/v2/articlesearch.json?fq=news_desk%3A%28%22Finance%22+%22Business%22+%22Market%22%29&sort=newest&api-key=932411dde075fd16337547bd13fdb616%3A11%3A69757573";

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		stream();
	}

	private static void stream()
	{
		arraylist = new ArrayList<HashMap<String, String>>();
		JSONArray json_result = null;
		//DataInputStream dis = new DataInputStream(System.in);

		try {
			// Retrieve JSON Objects from the given URL in JSONfunctions.class
			JSONObject json_data = JSONfunctions.getJSONfromURL(url);
			//	JSONObject json_data2 = JSONfuntions.getJSONfromURL(symbollist);
			//System.out.println("stream: "+json_data);
			try
			{
				JSONObject json_query = json_data.getJSONObject("response");
				json_result = json_query.getJSONArray("docs");

			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			if (json_result != null)
				for (int i = 0; i < json_result.length(); i++) 
				{
					//HashMap<String, String> map = new HashMap<String, String>();
					JSONObject vo = json_result.getJSONObject(i);
					//	JSONObject vo2 = json_result2.getJSONObject(1);
					//JSONObject vo = c.getJSONObject("volumeInfo");
					//map.put("title", vo.optString("title"));
					//map.put("description", vo.optString("description"));
					//JSONObject il = vo.getJSONObject("imageLinks");
					//map.put("thumbnail", il.optString("thumbnail"));
					//arraylist.add(map);
					//if (vo.optString("symbol").equals("^GDAXI") || vo.optString("symbol").equals("CAC") || vo.optString("symbol").equals("DIA") || vo.optString("symbol").equals("^IXIC") || vo.optString("symbol").equals("EURUSD=X") || vo.optString("symbol").equals("^FTSE"))
					//{
					//	System.out.println("\rSymbol is : " + vo2.optString("symbol"));
					System.out.print("\rURL: " + vo.optString("web_url").replace("\"", "") + "\nSource: " + vo.optString("source") + "\nHeadline: " + vo.optJSONObject("headline").optString("main") + "\nDate: " + vo.optString("pub_date") + "\nNews_Desk: " + vo.optString("news_desk") + "\ntype_of_material: " + vo.optString("type_of_material"));
					System.out.println("\n");
					//System.out.flush();
					//}

				}
			System.out.println("\r");
			try 
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
		catch (JSONException e) 
		{
			//Log.e("Error", e.getMessage());
			e.printStackTrace();
		}


	}

}
