Setting Up the Action Bar
	This Lesson Teaches You To:
		1.Support Android 3.0 and Above Only
			1) Theme.Holo
			2) <uses-sdk android:minSdkVersion="11"/>
		2.Support Android 2.1 and Above
			1) set up the v7 appcompat library
			2) public class MainActivity extends ActionBarActivity { ... }
			3) <uses-sdk android:minSdkVersion="7" android:targetSdkVersion="18" />
	You Should Also Read:
	Setting Up the Support Library

Adding Action Buttons
	This Lesson Teaches You To:
		1.Specify the Actions in XML
			in an XML menu resource
			<menu xmlns:android="http://schemas.android.com/apk/res/android" >
    			<!-- Search, should appear as action button -->
    			<item android:id="@+id/action_search"
          			android:icon="@drawable/ic_action_search"
         			android:title="@string/action_search"
          			android:showAsAction="ifRoom" />
    			<!-- Settings, should always be in the overflow -->
    			<item android:id="@+id/action_settings"
          			android:title="@string/action_settings"
          			android:showAsAction="never" />
			</menu>
			If your app is using the Support Library for compatibility on versions as low as Android 2.1
			xmlns:yourapp="http://schemas.android.com/apk/res-auto"
		2.Add the Actions to the Action Bar
			@Override
			public boolean onCreateOptionsMenu(Menu menu) {
   			// Inflate the menu items for use in the action bar
    			MenuInflater inflater = getMenuInflater();
    			inflater.inflate(R.menu.main_activity_actions, menu);
    			return super.onCreateOptionsMenu(menu);
			}
		3.Respond to Action Buttons
			@Override
			public boolean onOptionsItemSelected(MenuItem item) {
    			// Handle presses on the action bar items
    				switch (item.getItemId()) {
        			case R.id.action_search:
            				openSearch();
            				return true;
        			case R.id.action_settings:
            				openSettings();
            				return true;
        			default:
            				return super.onOptionsItemSelected(item);
   				}
			}
		4.Add Up Button for Low-level Activities
			performing Up navigation simply requires that you declare the parent activity in the manifest file and enable the Up button for the action bar.
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			// If your minSdkVersion is 11 or higher, instead use:
    			// getActionBar().setDisplayHomeAsUpEnabled(true);
	You Should Also Read:
		Providing Up Navigation

Styling the Action Bar
	This Lesson Teaches You To:
		1.Use an Android Theme
			Theme.Holo for a "dark" theme.
			Theme.Holo.Light for a "light" theme.
			<application android:theme="@android:style/Theme.Holo.Light" ... />

			Theme.AppCompat for the "dark" theme.
			Theme.AppCompat.Light for the "light" theme.
			Theme.AppCompat.Light.DarkActionBar for the light theme with a dark action bar
		2.Customize the Background
			For Android 3.0 and higher only
			<?xml version="1.0" encoding="utf-8"?>
			<resources>
    			<!-- the theme applied to the application or activity -->
    			<style name="CustomActionBarTheme"
           			parent="@android:style/Theme.Holo.Light.DarkActionBar">
        			<item name="android:actionBarStyle">@style/MyActionBar</item>
    			</style>
    			<!-- ActionBar styles -->
    			<style name="MyActionBar"
           			parent="@android:style/Widget.Holo.Light.ActionBar.Solid.Inverse">
        			<item name="android:background">@drawable/actionbar_background</item>
    			</style>
			</resources>
			<application android:theme="@style/CustomActionBarTheme" ... />

			For Android 2.1 and higher
			<?xml version="1.0" encoding="utf-8"?>
			<resources>
    			<!-- the theme applied to the application or activity -->
    			<style name="CustomActionBarTheme"
           			parent="@style/Theme.AppCompat.Light.DarkActionBar">
        			<item name="android:actionBarStyle">@style/MyActionBar</item>

        			<!-- Support library compatibility -->
        			<item name="actionBarStyle">@style/MyActionBar</item>
    			</style>

    			<!-- ActionBar styles -->
    			<style name="MyActionBar"
           			parent="@style/Widget.AppCompat.Light.ActionBar.Solid.Inverse">
        			<item name="android:background">@drawable/actionbar_background</item>

        		<!-- Support library compatibility -->
        		<item name="background">@drawable/actionbar_background</item>
    			</style>
			</resources>
			<application android:theme="@style/CustomActionBarTheme" ... />
		3.Customize the Text Color
			For Android 3.0 and higher only
			<?xml version="1.0" encoding="utf-8"?>
			<resources>
    			<!-- the theme applied to the application or activity -->
    			<style name="CustomActionBarTheme"
           			parent="@style/Theme.Holo">
        			<item name="android:actionBarStyle">@style/MyActionBar</item>
        			<item name="android:actionBarTabTextStyle">@style/MyActionBarTabText</item>
        			<item name="android:actionMenuTextColor">@color/actionbar_text</item>
    			</style>
    			<!-- ActionBar styles -->
    			<style name="MyActionBar"
           			parent="@style/Widget.Holo.ActionBar">
        			<item name="android:titleTextStyle">@style/MyActionBarTitleText</item>
    			</style>

    			<!-- ActionBar title text -->
    			<style name="MyActionBarTitleText"
           			parent="@style/TextAppearance.Holo.Widget.ActionBar.Title">
        			<item name="android:textColor">@color/actionbar_text</item>
    			</style>
    			<!-- ActionBar tabs text styles -->
    			<style name="MyActionBarTabText"
           			parent="@style/Widget.Holo.ActionBar.TabText">
        			<item name="android:textColor">@color/actionbar_text</item>
    			</style>
			</resources>

			For Android 2.1 and higher
			<?xml version="1.0" encoding="utf-8"?>
			<resources>
    			<!-- the theme applied to the application or activity -->
    			<style name="CustomActionBarTheme"
           			parent="@style/Theme.AppCompat">
        			<item name="android:actionBarStyle">@style/MyActionBar</item>
        			<item name="android:actionBarTabTextStyle">@style/MyActionBarTabText</item>
        			<item name="android:actionMenuTextColor">@color/actionbar_text</item>

        			<!-- Support library compatibility -->
        			<item name="actionBarStyle">@style/MyActionBar</item>
        			<item name="actionBarTabTextStyle">@style/MyActionBarTabText</item>
        			<item name="actionMenuTextColor">@color/actionbar_text</item>
    			</style>

    			<!-- ActionBar styles -->
    			<style name="MyActionBar"
           			parent="@style/Widget.AppCompat.ActionBar">
        			<item name="android:titleTextStyle">@style/MyActionBarTitleText</item>

        			<!-- Support library compatibility -->
        			<item name="titleTextStyle">@style/MyActionBarTitleText</item>
    			</style>

    			<!-- ActionBar title text -->
    			<style name="MyActionBarTitleText"
           			parent="@style/TextAppearance.AppCompat.Widget.ActionBar.Title">
        			<item name="android:textColor">@color/actionbar_text</item>
        			<!-- The textColor property is backward compatible with the Support Library -->
    			</style>

    			<!-- ActionBar tabs text -->
    			<style name="MyActionBarTabText"
           			parent="@style/Widget.AppCompat.ActionBar.TabText">
        			<item name="android:textColor">@color/actionbar_text</item>
        			<!-- The textColor property is backward compatible with the Support Library -->
    			</style>
			</resources>
		4.Customize the Tab Indicator
			For Android 3.0 and higher only
			<?xml version="1.0" encoding="utf-8"?>
			<resources>
    			<!-- the theme applied to the application or activity -->
    			<style name="CustomActionBarTheme"
           			parent="@style/Theme.Holo">
        			<item name="android:actionBarTabStyle">@style/MyActionBarTabs</item>
    			</style>

    			<!-- ActionBar tabs styles -->
    			<style name="MyActionBarTabs"
           			parent="@style/Widget.Holo.ActionBar.TabView">
        			<!-- tab indicator -->
        			<item name="android:background">@drawable/actionbar_tab_indicator</item>
    			</style>
			</resources>

			For Android 2.1 and higher
			<?xml version="1.0" encoding="utf-8"?>
			<resources>
    			<!-- the theme applied to the application or activity -->
    			<style name="CustomActionBarTheme"
           			parent="@style/Theme.AppCompat">
        			<item name="android:actionBarTabStyle">@style/MyActionBarTabs</item>

        			<!-- Support library compatibility -->
        			<item name="actionBarTabStyle">@style/MyActionBarTabs</item>
    			</style>

    			<!-- ActionBar tabs styles -->
    			<style name="MyActionBarTabs"
           			parent="@style/Widget.AppCompat.ActionBar.TabView">
        			<!-- tab indicator -->
        			<item name="android:background">@drawable/actionbar_tab_indicator</item>
				
        			<!-- Support library compatibility -->
        			<item name="background">@drawable/actionbar_tab_indicator</item>
    			</style>
			</resources>
	You Should Also Read:
		Styles and Themes
		Android Action Bar Style Generator

Overlaying the Action Bar
	This Lesson Teaches You To:
		Enable Overlay Mode
			For Android 3.0 and higher only
				<resources>
    				<!-- the theme applied to the application or activity -->
    				<style name="CustomActionBarTheme"
           				parent="@android:style/Theme.Holo">
        				<item name="android:windowActionBarOverlay">true</item>
    				</style>
				</resources>
			For Android 2.1 and higher
				<resources>
    				<!-- the theme applied to the application or activity -->
    				<style name="CustomActionBarTheme"
           				parent="@android:style/Theme.AppCompat">
        				<item name="android:windowActionBarOverlay">true</item>

        				<!-- Support library compatibility -->
        				<item name="windowActionBarOverlay">true</item>
    				</style>
				</resources>
		Specify Layout Top-margin
			<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    			android:layout_width="match_parent"
    			android:layout_height="match_parent"
    			android:paddingTop="?android:attr/actionBarSize">
    			...
			</RelativeLayout>
			If you're using the Support Library for the action bar, you need to remove the android: prefix. For example:
			<!-- Support library compatibility -->
			<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    			android:layout_width="match_parent"
    			android:layout_height="match_parent"
    			android:paddingTop="?attr/actionBarSize">
    			...
			</RelativeLayout>
	You Should Also Read:
		Styles and Themes