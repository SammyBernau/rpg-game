<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.10.2" name="npc_sprite" tilewidth="16" tileheight="23" tilecount="16" columns="4">
 <image source="../textures/samsRendition/humanoid/npc_sprite.png" width="64" height="94"/>
 <tile id="0" type="front_stand">
  <properties>
   <property name="Name" value="front_stand"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" name="front_player_bounding_box" type="bounding_box" x="1" y="1" width="14" height="19"/>
  </objectgroup>
 </tile>
 <tile id="2" type="front_walk_right">
  <properties>
   <property name="Name" value="front_walk_right"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" name="front_player_bounding_box" x="1" y="1" width="14" height="19"/>
  </objectgroup>
 </tile>
 <tile id="3" type="front_walk_left">
  <properties>
   <property name="Name" value="front_walk_left"/>
  </properties>
  <animation>
   <frame tileid="3" duration="61"/>
   <frame tileid="1" duration="61"/>
   <frame tileid="2" duration="61"/>
   <frame tileid="4" duration="61"/>
   <frame tileid="5" duration="61"/>
   <frame tileid="7" duration="61"/>
   <frame tileid="8" duration="61"/>
   <frame tileid="9" duration="61"/>
   <frame tileid="11" duration="61"/>
   <frame tileid="12" duration="61"/>
   <frame tileid="13" duration="61"/>
   <frame tileid="15" duration="61"/>
  </animation>
 </tile>
 <tile id="1" type="front_walk_right">
  <properties>
   <property name="Name" value="front_walk_right"/>
  </properties>
 </tile>
 <tile id="4" type="right_stand">
  <properties>
   <property name="Name" value="right_stand"/>
  </properties>
 </tile>
 <tile id="5" type="right_walk_right">
  <properties>
   <property name="Name" value="right_walk_right"/>
  </properties>
 </tile>
 <tile id="7" type="right_walk_left">
  <properties>
   <property name="Name" value="right_walk_left"/>
  </properties>
 </tile>
 <tile id="6" type="right_stand">
  <properties>
   <property name="Name" value="right_stand"/>
  </properties>
 </tile>
 <tile id="8" type="back_stand">
  <properties>
   <property name="Name" value="back_stand"/>
  </properties>
 </tile>
 <tile id="9" type="back_walk_right">
  <properties>
   <property name="Name" value="back_walk_right"/>
  </properties>
 </tile>
 <tile id="11" type="back_walk_left">
  <properties>
   <property name="Name" value="back_walk_left"/>
  </properties>
 </tile>
 <tile id="10" type="back_stand">
  <properties>
   <property name="Name" value="back_stand"/>
  </properties>
 </tile>
 <tile id="12" type="left_stand">
  <properties>
   <property name="Name" value="left_stand"/>
  </properties>
 </tile>
 <tile id="13" type="left_walk_left">
  <properties>
   <property name="Name" value="left_walk_left"/>
  </properties>
 </tile>
 <tile id="15" type="left_walk_right">
  <properties>
   <property name="Name" value="left_walk_right"/>
  </properties>
 </tile>
 <tile id="14" type="left_stand">
  <properties>
   <property name="Name" value="left_stand"/>
  </properties>
 </tile>
</tileset>
