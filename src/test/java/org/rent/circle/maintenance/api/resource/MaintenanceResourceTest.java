package org.rent.circle.maintenance.api.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.rent.circle.maintenance.api.dto.maintenance.SaveMaintenanceRequestDto;

@QuarkusTest
@TestHTTPEndpoint(MaintenanceResource.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
public class MaintenanceResourceTest {

    @Test
    public void Post_WhenGivenAValidRequestToSave_ShouldReturnSavedRequestId() {
        // Arrange
        SaveMaintenanceRequestDto saveMaintenanceRequestDto = SaveMaintenanceRequestDto.builder()
            .ownerId(1L)
            .residentId(2L)
            .propertyId(3L)
            .categoryId(4L)
            .description("description")
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(saveMaintenanceRequestDto)
            .when()
            .post("/request")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body(is("1"));
    }

    @Test
    public void Post_WhenGivenAnInValidPropertyToSave_ShouldReturnBadRequest() {
        // Arrange
        SaveMaintenanceRequestDto saveMaintenanceRequestDto = SaveMaintenanceRequestDto.builder()
            .ownerId(null)
            .residentId(2L)
            .propertyId(3L)
            .categoryId(4L)
            .description("description")
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(saveMaintenanceRequestDto)
            .when()
            .post("request")
            .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
