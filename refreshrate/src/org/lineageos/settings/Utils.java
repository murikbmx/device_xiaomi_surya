/*
 * Copyright (C) 2020 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.settings.refresh_rate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;

public class Utils {


    
    
    
    public static int getRefreshRate(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("pref_refresh_rate", Context.MODE_PRIVATE);
        return sharedPref.getInt("refresh_rate", 2);
    }    
    
    public static final void setRefreshRate(int v) {
        Parcel data = Parcel.obtain();
        data.writeInterfaceToken("android.ui.ISurfaceComposer");
        data.writeInt(v);
        try {
            ServiceManager.getService("SurfaceFlinger").transact(1035, data, (Parcel) null, 0);
        } catch (RemoteException e) {
            // nothing we can do
        }
        data.recycle();
    }
}
