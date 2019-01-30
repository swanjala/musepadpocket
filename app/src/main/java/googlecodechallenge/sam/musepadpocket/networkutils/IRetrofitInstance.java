package googlecodechallenge.sam.musepadpocket.networkutils;

import android.content.Context;
import googlecodechallenge.sam.musepadpocket.datamodel.apiinterfaces.ApiInterface;

public interface IRetrofitInstance {
      ApiInterface retrofitInstance(Context context);
}
