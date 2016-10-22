package io.alicorn.server.http;

import io.alicorn.data.jongothings.CocDbFacade;

import javax.inject.Inject;

public class CocEndpoint {
    private final CocDbFacade cocDbFacade;
    @Inject
    public CocEndpoint(SparkWrapper sparkWrapper, CocDbFacade cocDbFacade) {
        this.cocDbFacade = cocDbFacade;

        // Create
        sparkWrapper.post("/api/coc/create", (req, res) -> {
            return null;
        });

        // Retrieve

        // Update

        // Delete

    }
}
