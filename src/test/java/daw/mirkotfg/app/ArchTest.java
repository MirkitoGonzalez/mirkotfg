package daw.mirkotfg.app;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("daw.mirkotfg.app");

        noClasses()
            .that()
            .resideInAnyPackage("daw.mirkotfg.app.service..")
            .or()
            .resideInAnyPackage("daw.mirkotfg.app.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..daw.mirkotfg.app.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
