package org.deltaroboticsftc.scouting_app_v4.BackendlessTables;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

/**
 * Created by Luke Poellet on 5/1/2017.
 */

public class Games
{
    private String Table_Name;
    private String Game_Name;
    private String ownerId;
    private Boolean Published;
    private java.util.Date created;
    private String objectId;
    private String Game_Year;
    private java.util.Date updated;

    public String getTable_Name()
    {
        return Table_Name;
    }

    public void setTable_Name( String Table_Name )
    {
        this.Table_Name = Table_Name;
    }

    public String getGame_Name()
    {
        return Game_Name;
    }

    public void setGame_Name( String Game_Name )
    {
        this.Game_Name = Game_Name;
    }

    public String getOwnerId()
    {
        return ownerId;
    }

    public Boolean getPublished()
    {
        return Published;
    }

    public void setPublished( Boolean Published )
    {
        this.Published = Published;
    }

    public java.util.Date getCreated()
    {
        return created;
    }

    public String getObjectId()
    {
        return objectId;
    }

    public String getGame_Year()
    {
        return Game_Year;
    }

    public void setGame_Year( String Game_Year )
    {
        this.Game_Year = Game_Year;
    }

    public java.util.Date getUpdated()
    {
        return updated;
    }


    public Games save()
    {
        return Backendless.Data.of( Games.class ).save( this );
    }

    public void saveAsync( AsyncCallback<Games> callback )
    {
        Backendless.Data.of( Games.class ).save( this, callback );
    }

    public Long remove()
    {
        return Backendless.Data.of( Games.class ).remove( this );
    }

    public void removeAsync( AsyncCallback<Long> callback )
    {
        Backendless.Data.of( Games.class ).remove( this, callback );
    }

    public static Games findById( String id )
    {
        return Backendless.Data.of( Games.class ).findById( id );
    }

    public static void findByIdAsync( String id, AsyncCallback<Games> callback )
    {
        Backendless.Data.of( Games.class ).findById( id, callback );
    }

    public static Games findFirst()
    {
        return Backendless.Data.of( Games.class ).findFirst();
    }

    public static void findFirstAsync( AsyncCallback<Games> callback )
    {
        Backendless.Data.of( Games.class ).findFirst( callback );
    }

    public static Games findLast()
    {
        return Backendless.Data.of( Games.class ).findLast();
    }

    public static void findLastAsync( AsyncCallback<Games> callback )
    {
        Backendless.Data.of( Games.class ).findLast( callback );
    }

    public static List<Games> find( DataQueryBuilder queryBuilder )
    {
        return Backendless.Data.of( Games.class ).find( queryBuilder );
    }

    public static void findAsync( DataQueryBuilder queryBuilder, AsyncCallback<List<Games>> callback )
    {
        Backendless.Data.of( Games.class ).find( queryBuilder, callback );
    }
}