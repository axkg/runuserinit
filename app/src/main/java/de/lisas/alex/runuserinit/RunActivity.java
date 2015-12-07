/**
 *
 * RunUserinit is a trivial android app to run "userinit" scripts on Android devices
 * without init.d functionality.
 *
 * Copyright (C) 2015 Alexander Koenig
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *
 */

package de.lisas.alex.runuserinit;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_run, menu);
        return true;
    }

    static void runAsRoot() {
        try {
            // Replace run-parts as it's gone with CM13
            Process lsProcess = Runtime.getRuntime().exec(new String[] { "su", "-c", "ls /data/local/userinit.d/" });
            BufferedReader reader = new BufferedReader(new InputStreamReader(lsProcess.getInputStream()));

            String initScript;
            while ((initScript = reader.readLine()) != null) {
                // You better take care that these scripts daemonize whatever they run
                Process process = Runtime.getRuntime().exec(new String[] { "su", "-c", "/data/local/userinit.d/" + initScript });
                process.waitFor();
            }

            lsProcess.waitFor();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean runUserInit(View view) {
        runAsRoot();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
