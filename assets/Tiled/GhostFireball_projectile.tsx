<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.10.2" name="GhostFireball_projectile" class="projectile" tilewidth="17" tileheight="32" tilecount="1" columns="1">
 <properties>
  <property name="BodyType" value="dynamic"/>
 </properties>
 <image source="../textures/samsRendition/humanoid/GhostFireball_projectile.png" width="17" height="32"/>
 <tile id="0" type="ghost_fireball">
  <objectgroup draworder="index" id="2">
   <object id="1" name="bounding_box" x="0" y="0" width="17" height="32">
    <properties>
     <property name="BodyType" value="dynamic"/>
    </properties>
   </object>
  </objectgroup>
 </tile>
</tileset>
