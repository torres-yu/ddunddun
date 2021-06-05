package io.torres.ddunddun.util;

import java.lang.reflect.Field;

public class VoEntityConverter<Entity, Vo> {

    public void voToEntityConvert(Vo voIn, Entity entityOut){
        Field[] entityFields = entityOut.getClass().getDeclaredFields();
        for (Field entityField : entityFields) {
            try {
                Field voField = voIn.getClass().getDeclaredField(entityField.getName());
                if(voField.getType().equals(entityField.getType())){
                    entityField.setAccessible(true);
                    voField.setAccessible(true);
                    entityField.set(entityOut, voField.get(voIn));
                }
            } catch (NoSuchFieldException e) {
                continue;
            } catch (IllegalAccessException e){
                continue;
            }
        }
    }

    public void entityToVoConvert(Entity entityIn, Vo voOut){
        Field[] voFields = voOut.getClass().getDeclaredFields();
        for (Field voField : voFields) {
            try {
                Field entityField = entityIn.getClass().getDeclaredField(voField.getName());
                if(entityField.getType().equals(voField.getType())){
                    voField.setAccessible(true);
                    entityField.setAccessible(true);
                    voField.set(voOut, entityField.get(entityIn));
                }
            } catch (NoSuchFieldException e) {
                try {
                    if(entityIn.getClass().getSuperclass()!=null){
                        Field entityField = entityIn.getClass().getSuperclass().getDeclaredField(voField.getName());
                        if(entityField.getType().equals(voField.getType())){
                            voField.setAccessible(true);
                            entityField.setAccessible(true);
                            voField.set(voOut, entityField.get(entityIn));
                        }
                    }
                } catch (NoSuchFieldException eSu) {
                    try {
                        if(entityIn.getClass().getSuperclass().getSuperclass()!=null){
                            Field entityField = entityIn.getClass().getSuperclass().getSuperclass().getDeclaredField(voField.getName());
                            if(entityField.getType().equals(voField.getType())){
                                voField.setAccessible(true);
                                entityField.setAccessible(true);
                                voField.set(voOut, entityField.get(entityIn));
                            }
                        }
                    } catch (NoSuchFieldException eSuSu) {
                        continue;
                    } catch (IllegalAccessException eSuSu){
                        continue;
                    }
                    continue;
                } catch (IllegalAccessException eSu){
                    continue;
                }
                continue;
            } catch (IllegalAccessException e){
                continue;
            }
        }
    }
}
