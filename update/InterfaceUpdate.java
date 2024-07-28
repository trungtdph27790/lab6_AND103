package fpoly.trungtdph27790.lab1_firebase.lab5.update;


import fpoly.trungtdph27790.lab1_firebase.lab5.SvrResponseSanPham;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceUpdate {
    @FormUrlEncoded
    @POST("update.php")
    Call<SvrResponseSanPham> updateSanPham(
            @Field("MaSp") String MaSP,
            @Field("TenSp") String TenSP,
            @Field("Mota") String MoTa
    );
}
