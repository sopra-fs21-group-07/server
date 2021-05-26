package sopra.mapApi;

/**
 * Enum for concatenate the URL, static parts
 * @autor: Beat Furrer
 * @version: 1.0
 */
public enum GeoAdminApi {
    URL_MAP_SERVER("https://api3.geo.admin.ch/rest/services/api/MapServer/"),
    FIND_REQUEST("find?"),
    LAYER_NAMES("layer=ch.swisstopo.vec200-names-namedlocation"),
    SEARCH_TEXT("searchText="),
    SEARCH_FIELD("searchField=objname1"),
    AND ("&");

    public final String value;

    private GeoAdminApi(String value){
        this.value = value;
    }
}
