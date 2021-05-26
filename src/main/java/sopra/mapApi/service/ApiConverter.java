package sopra.mapApi.service;

import sopra.mapApi.GeoAdminApi;

public class ApiConverter {
    /**
     * Convert a user input summit name to a GeoAdmin API request
     * @autor: Beat Furrer
     * @version: 1.0
     */
    private String searchName = "";
    private String uri = "";

    /**
     * Constructor
     * @param searchName for the API searchText
     */
    public ApiConverter(String searchName){
        uri = getGeoAdminRequest(searchName);
    }

    /**
     * Returns the full URL
     * @return URL for find request on GeoAdmin Map
     */
    public String getUri() {
        return uri;
    }

    /**
     * Set the API for geo.admin.ch together
     * @param searchName : searchText field
     * @return uri for the REST API
     */
    private String getGeoAdminRequest(String searchName){
        return (GeoAdminApi.URL_MAP_SERVER.value +
                GeoAdminApi.FIND_REQUEST.value +
                GeoAdminApi.LAYER_NAMES.value +
                GeoAdminApi.AND.value +
                GeoAdminApi.SEARCH_TEXT.value +
                searchName +
                GeoAdminApi.AND.value +
                GeoAdminApi.SEARCH_FIELD.value);
    }
}
