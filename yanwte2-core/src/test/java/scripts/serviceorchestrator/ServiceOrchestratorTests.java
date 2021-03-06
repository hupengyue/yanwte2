package scripts.serviceorchestrator;

import com.github.winteryoung.yanwte2.core.ServiceOrchestrator;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import scripts.serviceorchestrator.testmaterial.simple.*;

/**
 * @author fanshen
 * @since 2017/12/12
 */
public class ServiceOrchestratorTests {
    @Test
    public void getOrchestratorByServiceType_normal() {
        NormalService orchestrator = ServiceOrchestrator.getOrchestrator(NormalService.class);
        Assertions.assertThat(orchestrator.apply(2)).isEqualTo(5);
    }

    @Test(
        expectedExceptions = IllegalStateException.class,
        expectedExceptionsMessageRegExp =
                "Cannot find orchestrator for service: .+?\\.ServiceWithoutOrchestrator"
    )
    public void getOrchestratorByServiceType_noOrchestratorDefined() {
        ServiceOrchestrator.getOrchestrator(ServiceWithoutOrchestrator.class);
    }

    @Test
    public void testServiceInvocation() {
        SimpleService simpleService = ServiceOrchestrator.getOrchestrator(SimpleService.class);
        String result = simpleService.apply("test");
        Assertions.assertThat(result).isEqualTo("test-abc");
    }
}
