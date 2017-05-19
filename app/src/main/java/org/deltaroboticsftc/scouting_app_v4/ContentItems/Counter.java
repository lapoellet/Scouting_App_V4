package org.deltaroboticsftc.scouting_app_v4.ContentItems;

import android.content.Context;
import android.util.Log;

/**
 * Created by Luke Poellet on 5/17/2017.
 */

public class Counter
{

    final private int itemKeyAttributes = 7;

    private String Title;
    private int Default = 0;
    private int Modifier = 1;
    private boolean hasMax = false;
    private int Maximum = 99;
    private boolean hasMin = false;
    private int Minimum = -99;

    public Counter(final String itemKey, final Context CONTEXT)
    {
        String subStringItem = itemKey;
        String lookUp;

        for (int loop = 0; loop < itemKeyAttributes; loop++)
        {
            lookUp = subStringItem.substring(subStringItem.indexOf("|") + 1, subStringItem.indexOf("|", subStringItem.indexOf("|") + 1));
            switch (loop)
            {
                case 2:
                    Title = lookUp;
                    break;

                case 3:
                    Default = Integer.parseInt(lookUp);
                    break;

                case 4:
                    Modifier = Integer.parseInt(lookUp);
                    break;

                case 5:
                    if (!lookUp.toLowerCase().equals("na"))
                    {
                        hasMax = true;
                        Maximum = Integer.parseInt(lookUp);
                    }
                    hasMax = false;
                    break;

                case 6:
                    if (!lookUp.toLowerCase().equals("na"))
                    {
                        hasMin = true;
                        Minimum = Integer.parseInt(lookUp);
                    }
                    hasMin = false;
                    break;

            }
            subStringItem = subStringItem.substring(subStringItem.indexOf(lookUp) + lookUp.length());
        }
        Log.i("Title", Title);
    }

}
