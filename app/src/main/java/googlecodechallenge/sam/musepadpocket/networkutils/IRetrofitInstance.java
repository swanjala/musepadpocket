package googlecodechallenge.sam.musepadpocket.networkutils;

import android.content.Context;
import googlecodechallenge.sam.musepadpocket.datamodel.apiinterfaces.RetrofitInterface;

public interface IRetrofitInstance {
      RetrofitInterface retrofitInstance(Context context);
}
