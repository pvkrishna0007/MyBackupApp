package appsforbb.com.mybackupapp.persistance;

import android.app.backup.BackupAgentHelper;
import android.app.backup.FileBackupHelper;
import android.widget.Toast;

import appsforbb.com.mybackupapp.MainActivity;

/**
 * A backup agent is used by the Android system,
 * it asks your app "What preference files do you want to save to the cloud"
 * @author paul.blundell
 *
 */
public class BlundellBackupAgent extends BackupAgentHelper {

    public static final String HELPER_KEY = "prefs";
    public static final String HELPER_FILE_KEY = "file_prefs";
	@Override
	public void onCreate() {
		super.onCreate();
		// A Helper for our Preferences, this name is the same name we use when saving SharedPreferences
//		SharedPreferencesBackupHelper helper = new SharedPreferencesBackupHelper(this, MainActivity.TUTORIAL_PREFERENCES);
//		addHelper(HELPER_KEY, helper);

//      keep file as backup.
        Toast.makeText(getApplicationContext(), "File Creation started.", Toast.LENGTH_SHORT).show();
        FileBackupHelper backupHelper=new FileBackupHelper(this,MainActivity.FILE_NAME);
        addHelper(HELPER_FILE_KEY, backupHelper);

        //FileBackupHelper hosts = new FileBackupHelper(this,"../databases/" + HostDatabase.DB_NAME);
        //addHelper(HELPER_DB_KEY, backupHelper);
    }
}