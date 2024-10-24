package kodlama.io.rentACar.business.abstracts;

import kodlama.io.rentACar.business.requests.CreateModelRequest;
import kodlama.io.rentACar.business.responses.GetAlModelsResponse;

import java.util.List;

public interface ModelService {
    List<GetAlModelsResponse> getAll();
    void add(CreateModelRequest createModelRequest);
}
