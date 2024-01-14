package org.acme.response;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection  // for native image mode
public class LabseqResponse {

    private int result;

    public LabseqResponse(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

}
