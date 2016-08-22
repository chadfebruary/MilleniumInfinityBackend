package com.milleniuminfinity.app.services.internet.Impl;


import com.milleniuminfinity.app.services.internet.InternetService;

/**
 * Created by Chad on 5/8/2016.
 */
public class InternetServiceImpl extends IntentService implements InternetService {

    private static final String ACTION_ADD = "com.milleniuminfinity.app.milleniuminfintity.services.internet.Impl.action.ADD";
    private static final String ACTION_UPDATE = "com.milleniuminfinity.app.milleniuminfintity.services.internet.Impl.action.UPDATE";

    private static final String EXTRA_ADD = "com.milleniuminfinity.app.milleniuminfintity.services.internet.Impl.action.ADD";
    private static final String EXTRA_UPDATE = "com.milleniuminfinity.app.milleniuminfintity.services.internet.Impl.action.UPDATE";

    private static final String TAG = "AddInternetService";
    private static InternetServiceImpl service = null;

    private InternetServiceImpl()
    {
        super("InternetServiceImpl");
    }

    public static InternetServiceImpl getInstance()
    {
        if(service == null)
            service = new InternetServiceImpl();

        return service;
    }



    @Override
    public void addInternet(Context context, Internet internet)
    {
        Intent intent = new Intent(context, InternetServiceImpl.class);
        intent.setAction(ACTION_ADD);
        //intent.putExtra(EXTRA_ADD, internet);
        context.startService(intent);
    }

    @Override
    public void updateInternet(Context context, Internet internet)
    {
        Intent intent = new Intent(context, InternetServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        //intent.putExtra(EXTRA_UPDATE, internet);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        if(intent != null) {
            final String action = intent.getAction();

            if (ACTION_ADD.equals(action)) {
                final Internet internet = (Internet) intent.getSerializableExtra(EXTRA_ADD);
                internetPost(internet);
            } else if (ACTION_UPDATE.equals(action)) {
                final Internet internet = (Internet) intent.getSerializableExtra(EXTRA_UPDATE);
                internetUpdate(internet);
            }
        }
    }

    private void internetUpdate(Internet internet)
    {
        //Post and Save local
        try
        {
            InternetRepository internetRepository = new InternetRepositoryImpl(getBaseContext());
            internetRepository.update(internet);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void internetPost(Internet internet)
    {
        //Post and Save local
        try
        {
            InternetRepository internetRepository = new InternetRepositoryImpl(getBaseContext());
            internetRepository.save(internet);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void deleteAll()
    {
        InternetRepository internetRepository = new InternetRepositoryImpl(getBaseContext());
        try {
            internetRepository.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
