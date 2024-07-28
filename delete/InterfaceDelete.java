package fpoly.trungtdph27790.lab1_firebase.lab5.delete;


import fpoly.trungtdph27790.lab1_firebase.lab5.SvrResponseSanPham;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceDelete {
    @FormUrlEncoded
    @POST("delete.php")
    Call<SvrResponseSanPham> deleteSanPham(
            @Field("MaSp") String MaSP
    );
}
