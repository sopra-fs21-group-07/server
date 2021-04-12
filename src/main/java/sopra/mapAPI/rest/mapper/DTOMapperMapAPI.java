package sopra.mapAPI.rest.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import sopra.mapAPI.entity.Summit;
import sopra.mapAPI.rest.dto.MapFoundGetDTO;

public interface DTOMapperMapAPI {
    DTOMapperMapAPI INSTANCE = Mappers.getMapper(DTOMapperMapAPI.class);

    @Mapping(source = "userInput", target = "userInput")
    String convertMapSearchToSearchText(String searchText);

    //@Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "altitude", target = "altitude")
    MapFoundGetDTO convertEntityToTourGetDTO(Summit summit);
}
