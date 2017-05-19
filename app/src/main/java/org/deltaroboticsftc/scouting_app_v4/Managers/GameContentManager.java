package org.deltaroboticsftc.scouting_app_v4.Managers;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.deltaroboticsftc.scouting_app_v4.ContentItems.Counter;

/**
 * Created by Luke Poellet on 5/15/2017.
 */

public class GameContentManager
{

    private static LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    private static LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    public static LinearLayout decode(final String content, final Context CONTEXT)
    {
        buttonParams.weight = 1;
        buttonParams.setMargins(5, 5, 5, 5);

        LinearLayout gameLayout = new LinearLayout(CONTEXT);
        gameLayout.setOrientation(LinearLayout.VERTICAL);
        gameLayout.setLayoutParams(params);

        String contentSplit = content;
        String item;

        while (!contentSplit.toLowerCase().equals("@null!"))
        {

            item = contentSplit.substring(contentSplit.indexOf("@"), contentSplit.indexOf("!") + 1);
            contentSplit = contentSplit.substring(contentSplit.indexOf("!") + 1);
            Log.d("Next Item", item);

            LinearLayout newItem = buildItem(item, CONTEXT);
            if (newItem != null)
            {
                gameLayout.addView(newItem);
            }

        }

        return gameLayout;
    }


    private static LinearLayout buildItem(final String itemKey, final Context context)
    {
        LinearLayout item = null;

        if (itemKey.contains("|+|"))
        {
            if (itemKey.contains("|C|"))
            {
                new Counter(itemKey, context);
            }

            if (itemKey.contains("|R|"))
            {

            }

            if (itemKey.contains("|T|"))
            {

            }

            if (itemKey.contains("|S|"))
            {

            }
        }

        return item;
    }



}
