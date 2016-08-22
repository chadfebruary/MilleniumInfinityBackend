package com.milleniuminfinity.app.services.shop.Impl;


import com.milleniuminfinity.app.services.shop.ShopService;

/**
 * Created by Chad on 5/8/2016.
 */
public class ShopServiceImpl extends IntentService implements ShopService {
    //private final ShopRepository repository;
    private static final String ACTION_ADD = "app.milleniuminfinity.com.assignment62.services.shop.Impl.action.ADD";
    private static final String ACTION_UPDATE = "app.milleniuminfinity.com.assignment62.services.shop.Impl.action.UPDATE";

    private static final String EXTRA_ADD = "app.milleniuminfinity.com.assignment62.services.shop.Impl.action.ADD";
    private static final String EXTRA_UPDATE = "app.milleniuminfinity.com.assignment62.services.shop.Impl.action.UPDATE";

    private static final String TAG = "AddShop";
    private static ShopServiceImpl service = null;



    private ShopServiceImpl()
    {
        super("ShopServiceImpl");
        //repository = new ShopRepositoryImpl(App.getAppContext());
    }

    public static ShopServiceImpl getInstance()
    {
        if(service == null)
            service = new ShopServiceImpl();

        return service;
    }

    @Override
    public void addShop(Context context, Shop shop)
    {
        Intent intent = new Intent(context, ShopServiceImpl.class);
        intent.setAction(ACTION_ADD);
        //intent.putExtra(EXTRA_ADD, shop);
        context.startService(intent);
    }

    @Override
    public void updateShop(Context context, Shop shop)
    {
        Intent intent = new Intent(context, ShopServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        //intent.putExtra(EXTRA_UPDATE, shop);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {

            final String action = intent.getAction();

            if (ACTION_ADD.equals(action))
            {
                final Shop shop = (Shop) intent.getSerializableExtra(EXTRA_ADD);
                shopPost(shop);
            }
            else if (ACTION_UPDATE.equals(action))
            {
                final Shop shop = (Shop) intent.getSerializableExtra(EXTRA_UPDATE);
                shopUpdate(shop);
            }
        }
    }

    private void shopUpdate(Shop shop)
    {
        //Post and Save local
        try
        {
            ShopRepository shopRepository = new ShopRepositoryImpl(getBaseContext());

            shopRepository.update(shop);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void shopPost(Shop shop)
    {
        //Post and save local
        try
        {
            ShopRepository shopRepository = new ShopRepositoryImpl(getBaseContext());
            shopRepository.update(shop);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void deleteAll()
    {
        ShopRepository shopRepository = new ShopRepositoryImpl(getBaseContext());

        try
        {
            shopRepository.deleteAll();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
