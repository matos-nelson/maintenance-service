package org.rent.circle.maintenance.api.resource;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import java.util.List;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.rent.circle.maintenance.api.annotation.AuthUser;
import org.rent.circle.maintenance.api.dto.category.CategoryDto;

@QuarkusTest
@TestHTTPEndpoint(CategoryResource.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
@AuthUser
public class CategoryResourceTest {

    @Test
    public void GET_WhenCalled_ShouldReturnCategories() {
        // Arrange

        // Act
        List<CategoryDto> result = given()
            .when()
            .get()
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .as(new TypeRef<>() {
            });

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
