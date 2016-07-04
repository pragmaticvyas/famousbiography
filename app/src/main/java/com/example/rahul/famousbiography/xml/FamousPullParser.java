package com.example.rahul.famousbiography.xml;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.util.Log;
import com.example.rahul.famousbiography.R;
import com.example.rahul.famousbiography.model.FamousPeople;

public class FamousPullParser {

	private static final String LOGTAG = "EXPLORECA";
	
	private static final String FAMOUS_ID = "famousid";
	private static final String FAMOUS_TITLE = "name";
	private static final String FAMOUS_DESC = "details";
	private static final String FAMOUS_IMAGE = "image";
	
	private FamousPeople currentFamousPeople = null;
	private String currentData = null;
	List<FamousPeople> famouspeoplexmldata = new ArrayList<>();

	public List<FamousPeople> parseXML(Context context) {

		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			
			InputStream stream = context.getResources().openRawResource(R.raw.famouspeople);
			xpp.setInput(stream, null);

			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					handleStartTag(xpp.getName());
				} else if (eventType == XmlPullParser.END_TAG) {
					currentData = null;
				} else if (eventType == XmlPullParser.TEXT) {
					handleText(xpp.getText());
				}
				eventType = xpp.next();
			}

		} catch (NotFoundException e) {
			Log.d(LOGTAG, e.getMessage());
		} catch (XmlPullParserException e) {
			Log.d(LOGTAG, e.getMessage());
		} catch (IOException e) {
			Log.d(LOGTAG, e.getMessage());
		}

		return famouspeoplexmldata;
	}

	private void handleText(String text) {
		String xmlText = text;
		if (currentFamousPeople != null && currentData != null) {
			if (currentData.equals(FAMOUS_ID)) {
				Integer id = Integer.parseInt(xmlText);
				currentFamousPeople.setId(id);
			} 
			else if (currentData.equals(FAMOUS_TITLE)) {
				currentFamousPeople.setName(xmlText);
			}
			else if (currentData.equals(FAMOUS_DESC)) {
				currentFamousPeople.setDetails(xmlText);
			}
			else if (currentData.equals(FAMOUS_IMAGE)) {
				currentFamousPeople.setPhoto(xmlText);
			}

		}
	}

	private void handleStartTag(String name) {
		if (name.equals("famous")) {
			currentFamousPeople = new FamousPeople();
			famouspeoplexmldata.add(currentFamousPeople);
		}
		else {
			currentData = name;
		}
	}
}
