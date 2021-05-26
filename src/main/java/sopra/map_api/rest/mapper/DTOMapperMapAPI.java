package sopra.map_api.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import sopra.map_api.entity.Summit;
import sopra.map_api.rest.dto.MapFoundGetDTO;
import sopra.map_api.rest.dto.MapSearchPostDTO;

@Mapper
public interface DTOMapperMapAPI {
    DTOMapperMapAPI INSTANCE = Mappers.getMapper(DTOMapperMapAPI.class);

    //Never use
    @Mapping(source = "userInput", target = "name")
    Summit convertMapSearchToSearchText(MapSearchPostDTO searchText);

    //Information back tot the frontend
    @Mapping(source = "name", target = "name")
    @Mapping(source = "altitude", target = "altitude")
    @Mapping(source = "x", target="x")
    @Mapping(source="y", target="y")
    MapFoundGetDTO convertEntityToTourGetDTO(Summit summit);
}
