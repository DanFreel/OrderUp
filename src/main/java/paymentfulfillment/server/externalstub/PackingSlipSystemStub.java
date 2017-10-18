package paymentfulfillment.server.externalstub;


import paymentfulfillment.server.core.Result;
import paymentfulfillment.server.core.Result.Status;
import paymentfulfillment.server.core.PackingSlip;

import java.util.List;

public class PackingSlipSystemStub {
    public Result generatePackingSlips(List<PackingSlip> packingSlips) {
        return new Result(Status.SUCCESS, "Generating the following packing slips: " + packingSlips);
    }
}
