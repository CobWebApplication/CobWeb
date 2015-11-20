package cobwebinc.cobweb;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cobwebinc.cobweb.facebook.FacebookFragment;
import cobwebinc.cobweb.twitter.MyTwitter;
import cobwebinc.cobweb.twitter.TwitterFragment;
import twitter4j.ResponseList;
import twitter4j.Status;

public class LentaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lenta);

        MyTwitter twitter = new MyTwitter(this);
        ResponseList<Status> statuses = twitter.getHomeTimeLine();

//        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.mainLineatLayout);
//        LinearLayout.LayoutParams lp =
//                (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
//        lp.setMargins(0, 2, 0, 2);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        for (int i = 0; i < statuses.size(); i++) {
            TwitterFragment fragment = TwitterFragment.newInstanse(statuses.get(i));
            fragmentTransaction.add(R.id.mainLineatLayout , fragment, "tf" + String.valueOf(i));
        }

        FacebookFragment myFragment = new FacebookFragment();
        fragmentTransaction.add(R.id.mainLineatLayout , myFragment, "tf" + String.valueOf(statuses.size()));

        fragmentTransaction.commit();

    }

}
