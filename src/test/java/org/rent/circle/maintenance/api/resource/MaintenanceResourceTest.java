package org.rent.circle.maintenance.api.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.rent.circle.maintenance.api.dto.maintenance.SaveMaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.UpdateMaintenanceRequestDto;
import org.rent.circle.maintenance.api.enums.Status;

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
            .post()
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body(is("1"));
    }

    @Test
    public void Post_WhenGivenAnInValidRequestToSave_ShouldReturnBadRequest() {
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
            .post()
            .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void PATCH_WhenMaintenanceRequestWithGivenIdDoesNotExist_ShouldReturnNoContent() {
        // Arrange
        UpdateMaintenanceRequestDto updateMaintenanceRequestDto = UpdateMaintenanceRequestDto
            .builder()
            .maintenanceRequestId(1000L)
            .status(Status.COMPLETED)
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(updateMaintenanceRequestDto)
            .when()
            .patch()
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void PATCH_WhenMaintenanceRequestIsNotValid_ShouldReturnNoContent() {
        // Arrange
        UpdateMaintenanceRequestDto updateMaintenanceRequestDto = UpdateMaintenanceRequestDto
            .builder()
            .maintenanceRequestId(200L)
            .status(Status.COMPLETED)
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(updateMaintenanceRequestDto)
            .when()
            .patch()
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void PATCH_WhenMaintenanceRequestIsValid_ShouldReturnMaintenanceRequest() {
        // Arrange
        UpdateMaintenanceRequestDto updateMaintenanceRequestDto = UpdateMaintenanceRequestDto
            .builder()
            .maintenanceRequestId(100L)
            .status(Status.COMPLETED)
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(updateMaintenanceRequestDto)
            .when()
            .patch()
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body("ownerId", is(1),
                "residentId", is(1),
                "propertyId", is(1),
                "description", is("Windows"),
                "status", is("COMPLETED"),
                "completedAt", is(notNullValue()),
                "category.id", is(1),
                "category.name", is("Appliance"));
    }

    @Test
    public void GET_WhenAMaintenanceRequestCantBeFound_ShouldReturnNoContent() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/123/owner/1")
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void GET_WhenAMaintenanceRequestIsFound_ShouldReturnMaintenanceRequest() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/100/owner/1")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body("ownerId", is(1),
                "residentId", is(1),
                "propertyId", is(1),
                "description", is("Windows"),
                "status", is("IN_PROGRESS"),
                "completedAt", is(nullValue()),
                "category.id", is(1),
                "category.name", is("Appliance"));
    }
}
