package baguchan.bagusmob;

import baguchan.bagusmob.entity.Modifiger;
import baguchan.bagusmob.entity.Ninjar;
import baguchan.bagusmob.entity.Tengu;
import baguchan.bagusmob.entity.VilerVex;
import baguchan.bagusmob.registry.ModItemRegistry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BagusMob.MODID)
public class EntityEvent {

	@SubscribeEvent()
	public static void addSpawn(EntityJoinLevelEvent event) {
		if (event.getEntity() instanceof Villager) {
			Villager abstractVillager = (Villager) event.getEntity();

			abstractVillager.goalSelector.addGoal(1, new AvoidEntityGoal(abstractVillager, Tengu.class, 16.0F, 0.75F, 0.8F));
			abstractVillager.goalSelector.addGoal(1, new AvoidEntityGoal(abstractVillager, Ninjar.class, 16.0F, 0.75F, 0.8F));
			abstractVillager.goalSelector.addGoal(1, new AvoidEntityGoal(abstractVillager, VilerVex.class, 16.0F, 0.75F, 0.8F));
			abstractVillager.goalSelector.addGoal(1, new AvoidEntityGoal(abstractVillager, Modifiger.class, 16.0F, 0.75F, 0.8F));
		}

		if (event.getEntity() instanceof WanderingTrader) {
			WanderingTrader wanderingTraderEntity = (WanderingTrader) event.getEntity();

			wanderingTraderEntity.goalSelector.addGoal(1, new AvoidEntityGoal(wanderingTraderEntity, Tengu.class, 16.0F, 0.75F, 0.8F));
			wanderingTraderEntity.goalSelector.addGoal(1, new AvoidEntityGoal(wanderingTraderEntity, Ninjar.class, 16.0F, 0.75F, 0.8F));
			wanderingTraderEntity.goalSelector.addGoal(1, new AvoidEntityGoal(wanderingTraderEntity, VilerVex.class, 16.0F, 0.75F, 0.8F));
			wanderingTraderEntity.goalSelector.addGoal(1, new AvoidEntityGoal(wanderingTraderEntity, Modifiger.class, 16.0F, 0.75F, 0.8F));
		}

	}

	@SubscribeEvent
	public static void visionPercent(LivingEvent.LivingVisibilityEvent event) {
		if (event.getLookingEntity() != null) {
			ItemStack itemstack = event.getEntity().getItemBySlot(EquipmentSlot.HEAD);
			if (itemstack.is(ModItemRegistry.NINJA_HOOD.get())) {
				event.modifyVisibility(0.5D);
			}
			ItemStack itemstack2 = event.getEntity().getItemBySlot(EquipmentSlot.CHEST);
			if (itemstack2.is(ModItemRegistry.NINJA_CHESTPLATE.get())) {
				event.modifyVisibility(0.5D);
			}
			ItemStack itemstack3 = event.getEntity().getItemBySlot(EquipmentSlot.FEET);
			if (itemstack3.is(ModItemRegistry.NINJA_BOOTS.get())) {
				event.modifyVisibility(0.85D);
			}
		}
	}
}