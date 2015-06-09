package appsforbb.com.mybackupapp;


import android.app.Activity;
import android.app.backup.BackupManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {

    private EditText inputEditText;
    private TextView displayTextView;
    public static final String TUTORIAL_PREFERENCES = "TutorialPreferences";
    protected static final String NAME = "Name";
    public static final String FILE_NAME = "abc.txt";
    private File file_backup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        file_backup = new File(getFilesDir(), FILE_NAME);


        inputEditText = (EditText) findViewById(R.id.main_edit_text_input);
        displayTextView = (TextView) findViewById(R.id.main_text_view_display);

        // Backup manager is what tells the android system about your preferences
        //BackupManager backupManager = new BackupManager(this);

        /*displayTextView.setText(getSharedPreference() *//*+ "/" + readDataInFile()*//*);
        inputEditText.setText(getSharedPreference());
        Log.d("MainActivity", "onCreate : (line 43) " + getSharedPreference());*/

        displayTextView.setText(readDataInFile() /*+ "/" + readDataInFile()*/);
        inputEditText.setText(readDataInFile());
        Log.d("MainActivity", "onCreate : (line 43) " + readDataInFile());
    }

    public void onSaveInput(View button) {
        String input = inputEditText.getText().toString();
        // Check the user has entered some text

        if (!"".equals(input)) {
            // Update the UI
            displayTextView.setText(input);
            // Save to shared preferences
            //saveSharedPreferences(input);
            Log.d("MainActivity", "onSaveInput : (line 57) " + input);
           saveDataInFile(input);

            BackupManager backupManager = new BackupManager(this);
            backupManager.dataChanged();

            Toast.makeText(this, "T:" + readDataInFile(), Toast.LENGTH_SHORT).show();
        }
    }


  /*  public void saveSharedPreferences(String value) {
        SharedPreferences sharedPreferences = getSharedPreferences(TUTORIAL_PREFERENCES, MODE_PRIVATE);
        sharedPreferences.edit().putString(NAME, value).commit();
    }

    public String getSharedPreference() {
        SharedPreferences sharedPreferences = getSharedPreferences(TUTORIAL_PREFERENCES, MODE_PRIVATE);
        return sharedPreferences.getString(NAME, "Invalid");
    }*/

    public void saveDataInFile(String data) {
        try {
            FileOutputStream fos = new FileOutputStream(file_backup);
            fos.write(data.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readDataInFile() {
        try {
            FileInputStream fis = new FileInputStream(file_backup);

            if (fis.available() == 0)
                return "Empty";
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error";
        }
    }


    /**
     * adb shell bmgr enable true
     *
     * # Trigger a backup, usage:
     # adb shell bmgr backup <package>

     # schedule backup
     adb shell bmgr backup com.vogella.android.databackup
     # ensure scheduled backup run
     adb shell bmgr run

     # to restore you backup use bmgr restore
     adb shell bmgr restore com.vogella.android.databackup
     */

    // for getting the meta data key..
    //https://developer.android.com/google/backup/signup.html

    // for setting the backup at first time.
    // adb shell bmgr enable true
    // adb shell bmgr backup appsforbb.com.mybackupapp
    // adb shell bmgr run

    // for next backup onwards just execute run command only.
    // adb shell bmgr run
}

//class MyFileBackupAgent extends BackupAgentHelper {
//    // The name of the file
//    static final String TOP_SCORES = "scores";
//    static final String PLAYER_STATS = "stats";
//
//    // A key to uniquely identify the set of backup data
//    static final String FILES_BACKUP_KEY = "myfiles";
//
//    // Allocate a helper and add it to the backup agent
//    @Override
//    public void onCreate() {
//        FileBackupHelper helper = new FileBackupHelper(this,
//                TOP_SCORES, PLAYER_STATS);
//        addHelper(FILES_BACKUP_KEY, helper);
//    }
//
//    public void storeDatabase(){
//        FileBackupHelper hosts = new FileBackupHelper(this,
//                "../databases/" + HostDatabase.DB_NAME);
//        addHelper(HostDatabase.DB_NAME, hosts);
//    }
//}