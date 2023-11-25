package org.rent.circle.maintenance.api.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.JwtSecurity;
import io.restassured.common.mapper.TypeRef;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.rent.circle.maintenance.api.annotation.AuthUser;
import org.rent.circle.maintenance.api.dto.maintenance.MaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.SaveMaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.UpdateMaintenanceRequestDto;
import org.rent.circle.maintenance.api.enums.Status;

@QuarkusTest
@TestHTTPEndpoint(MaintenanceResource.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
@AuthUser
public class MaintenanceResourceTest {

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    @TestSecurity(user = "test_user")
    @JwtSecurity(claims = {
        @Claim(key = "user_id", value = "123456")
    })
    public @interface SingleMaintenanceRequestUser {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    @TestSecurity(user = "new_user")
    @JwtSecurity(claims = {
        @Claim(key = "user_id", value = "def456")
    })
    public @interface NoMaintenanceRequestUser {

    }

    @Test
    public void Post_WhenGivenAValidRequestToSave_ShouldReturnSavedRequestId() {
        // Arrange
        SaveMaintenanceRequestDto saveMaintenanceRequestDto = SaveMaintenanceRequestDto.builder()
            .residentId(2L)
            .propertyId(3L)
            .categoryId(4L)
            .description("description")
            .instructions("instructions")
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
            .residentId(null)
            .propertyId(3L)
            .categoryId(4L)
            .description("description")
            .instructions("instructions")
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
    @SingleMaintenanceRequestUser
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
            .body("managerId", is("auth_user"),
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
            .get("/123")
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
            .get("/100")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body("managerId", is("auth_user"),
                "residentId", is(1),
                "propertyId", is(1),
                "description", is("Windows"),
                "instructions", is("Instructions"),
                "status", is("IN_PROGRESS"),
                "completedAt", is(nullValue()),
                "category.id", is(1),
                "category.name", is("Appliance"));
    }

    @Test
    @NoMaintenanceRequestUser
    public void GET_getMaintenanceRequests_WhenRequestsCantBeFound_ShouldReturnNoRequests() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("?page=0&pageSize=10")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body(is("[]"));
    }

    @Test
    @SingleMaintenanceRequestUser
    public void GET_getMaintenanceRequests_WhenRequestsAreFound_ShouldReturnRequests() {
        // Arrange

        // Act
        List<MaintenanceRequestDto> result = given()
            .when()
            .get("?page=0&pageSize=10")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .as(new TypeRef<>() {
            });

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("test_user", result.get(0).getManagerId());
        assertEquals(2L, result.get(0).getResidentId());
        assertEquals(2L, result.get(0).getPropertyId());
        assertEquals(2L, result.get(0).getCategory().getId());
        assertEquals("Doors/Keys", result.get(0).getCategory().getName());
        assertEquals("Windows", result.get(0).getDescription());
        assertEquals("Instructions", result.get(0).getInstructions());
        assertEquals("2 Note", result.get(0).getNote());
        assertEquals("COMPLETED", result.get(0).getStatus());
    }

    @Test
    public void GET_getMaintenanceRequests_WhenFailsValidation_ShouldReturnBadRequest() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("?page=0")
            .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
