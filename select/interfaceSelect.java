package fpoly.trungtdph27790.lab1_firebase.lab5.select;

import retrofit2.Call;
import retrofit2.http.GET;

public interface interfaceSelect {
    @GET("select.php")
    Call<SvrResponseSelect> selectSanPham();
}
