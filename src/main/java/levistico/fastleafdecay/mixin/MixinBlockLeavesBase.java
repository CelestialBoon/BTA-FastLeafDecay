package levistico.fastleafdecay.mixin;

import levistico.fastleafdecay.Main;
import net.minecraft.src.BlockLeavesBase;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(value = BlockLeavesBase.class, remap = false)
public class MixinBlockLeavesBase {
    @Inject(method = "updateTick (Lnet/minecraft/src/World;IIILjava/util/Random;)V", at = @At(value="INVOKE", target = "Lnet/minecraft/src/BlockLeavesBase;removeLeaves(Lnet/minecraft/src/World;III)V"))
    private void updateInject(World world, int x, int y, int z, Random random, CallbackInfo ci) {
        for(int i = -3; i <= 3; i++) {
            for(int j = -3; j<=3; j++) {
                for(int k = -3; k<=3; k++) {
                    world.scheduleBlockUpdate(x+i, y+j, z+k, ((BlockLeavesBase)(Object)this).blockID, Main.bound/10 + (random.nextInt(Main.bound) * 9 / 10));
                }
            }
        }
    }
}
