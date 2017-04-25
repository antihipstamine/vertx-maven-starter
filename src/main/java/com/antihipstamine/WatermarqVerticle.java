package com.antihipstamine;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.example.util.Runner;
import io.vertx.ext.web.Router;

/**
 * Created by vish on 24/04/2017.
 */
public class WatermarqVerticle extends AbstractVerticle {

  // Convenience method so you can run it in your IDE
  public static void main(String[] args) {
    Runner.runExample(WatermarqVerticle.class);
  }

  @Override
  public void start() {

    OpenStegoWrapper wrapper = new OpenStegoWrapper();

    Router router = Router.router(vertx);

    // signature handler
    router.get("/signature/:sigfile/:password").handler(routingContext -> {
      HttpServerResponse response = routingContext.response();
      response.putHeader("content-type", "text/plain");
      String sigfile = routingContext.request().getParam("sigfile");
      String password = routingContext.request().getParam("password");
      response.end("handleSignature: " + sigfile + " / " + password);
    });

    // watermark handler
    router.get("/watermark/:sigfile/:outputfile").handler(routingContext -> {
      HttpServerResponse response = routingContext.response();
      response.putHeader("content-type", "text/plain");
      String sigfile = routingContext.request().getParam("sigfile");
      String outputfile = routingContext.request().getParam("outputfile");
      response.end("handleWatermark: " + sigfile + " / " + outputfile);
    });

    vertx.createHttpServer().requestHandler(router::accept).listen(8080);
  }

  private void sendError(int statusCode, HttpServerResponse response) {
    response.setStatusCode(statusCode).end();
  }
}
