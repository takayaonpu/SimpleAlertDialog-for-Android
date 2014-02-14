/*
 * Copyright 2012 Soichiro Kashima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.simplealertdialog;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;

/**
 * @author Soichiro Kashima
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SimpleAlertDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        return new SimpleAlertDialog.Helper<SimpleAlertDialogFragment, Fragment, Activity>() {
            public Activity getActivity() {
                return SimpleAlertDialogFragment.this.getActivity();
            }

            public Fragment getTargetFragment() {
                return SimpleAlertDialogFragment.this.getTargetFragment();
            }
        }.createDialog(args, getTargetFragment(), getActivity());
    }

    public static class Builder extends
            SimpleAlertDialog.Builder<SimpleAlertDialogFragment, Fragment> {
        private Fragment mTargetFragment;

        @Override
        public Builder setTargetFragment(final Fragment targetFragment) {
            mTargetFragment = targetFragment;
            return this;
        }

        @Override
        public SimpleAlertDialogFragment create() {
            Bundle args = createArguments();
            SimpleAlertDialogFragment fragment = new SimpleAlertDialogFragment();
            fragment.setArguments(args);
            if (mTargetFragment != null) {
                fragment.setTargetFragment(mTargetFragment, 0);
            }
            return fragment;
        }
    }

}