package sopra.mapAPI;

/**
 * Enum for concatenate the URL, static parts
 * @autor: Beat Furrer
 * @version: 1.0
 */
public enum GeoAdminApi {
    URLMapServer ("https://api3.geo.admin.ch/rest/services/api/MapServer/"),
    findRequest ("find?"),
    layerNames ("layer=ch.swisstopo.vec200-names-namedlocation"),
    searchText ("searchText="),
    searchField ("searchField=objname1"),
    AND ("&");

    public final String value;

    private GeoAdminApi(String value){
        this.value = value;
    }
}
