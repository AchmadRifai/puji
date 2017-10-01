package wahyono.puji.projectskripsi.ws;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ai on 14/08/2017.
 */

public interface Api {
    @GET("json/pesan/{m}")
    Call<BahanNota>getNota(@Path("m") String nomor);

    @GET("json/pesan/{nota}/{menu}/{qty}")
    Call<Msg>tumpukPesanan(@Path("nota") String nota,@Path("menu") String menu,@Path("qty") String qty);

    @GET("json/pesan/daftar/{nota}")
    Call<List<ItemBrg>>getDaftar(@Path("nota")String nota);

    @GET("json/pesan/batal/{nota}")
    Call<Msg>batalTrans(@Path("nota")String nota);

    @GET("json/pesan/valid/{nota}")
    Call<Msg>validTrans(@Path("nota")String nota);

    @GET("json/menu/kat")
    Call<List<Kat>>getKategori();

    @GET("json/menu/menu/")
    Call<List<Menu>>getMenu();

    @GET("json/menu/menu/{kat}")
    Call<List<Menu>>getMenu(@Path("kat") int kat);

    @GET("json/pesan/delItem/{nota}/{menu}")
    Call<Msg>delItemPesan(@Path("nota") String nota,@Path("menu") String menu);
}