import com.github.alphafoxz.oneboot.preset_sys.gen.db.entity.PsysUser;
import com.github.alphafoxz.oneboot.preset_sys.gen.db.repo.PsysUserRepo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PsysTest {
    @Resource
    PsysUserRepo psysUserRepo;

    @Test
    public void test() {
        PsysUser users = new PsysUser().setId(1L);
        psysUserRepo.saveAndFlush(users);
    }
}
