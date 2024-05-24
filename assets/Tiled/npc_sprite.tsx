<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.10.2" name="npc_sprite" tilewidth="16" tileheight="23" tilecount="16" columns="4">
 <image source="../textures/samsRendition/humanoid/npc_sprite.png" width="64" height="94"/>
 <tile id="2">
  <objectgroup draworder="index" id="2">
   <object id="1" name="front_player_bounding_box" x="1" y="1" width="14" height="19"/>
  </objectgroup>
 </tile>
 <tile id="0" type="stand">
  <properties>
   <property name="Name" value="front_stand"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" name="front_player_bounding_box" type="bounding_box" x="1" y="1" width="14" height="19"/>
  </objectgroup>
 </tile>
 <tile id="1" type="front">
  <properties>
   <property name="Name" value="front_walk_right"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="2" width="14" height="19"/>
  </objectgroup>
 </tile>
 <tile id="3" type="front">
  <properties>
   <property name="Name" value="front_walk_left"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="2" width="14" height="19"/>
  </objectgroup>
 </tile>
 <tile id="4">
  <properties>
   <property name="Name" value=""/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="2" y="1" width="13" height="21"/>
   <object id="2" x="2" y="1" width="13" height="19"/>
  </objectgroup>
 </tile>
 <tile id="5" type="right">
  <properties>
   <property name="Name" value="right_walk_right"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="2" y="2" width="13" height="19"/>
  </objectgroup>
 </tile>
 <tile id="6" type="stand">
  <properties>
   <property name="Name" value="right_stand"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="2" y="1" width="13" height="19"/>
  </objectgroup>
 </tile>
 <tile id="7" type="right">
  <properties>
   <property name="Name" value="right_walk_left"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="2" y="2" width="13" height="19"/>
  </objectgroup>
 </tile>
 <tile id="8">
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="1" width="14" height="22"/>
   <object id="2" x="1" y="1" width="14" height="19"/>
  </objectgroup>
 </tile>
 <tile id="10" type="stand">
  <properties>
   <property name="Name" value="back_stand"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="1" width="14" height="19"/>
  </objectgroup>
 </tile>
 <tile id="9" type="back">
  <properties>
   <property name="Name" value="back_walk_right"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="2" width="14" height="19"/>
  </objectgroup>
 </tile>
 <tile id="11" type="back">
  <properties>
   <property name="Name" value="back_walk_left"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="2" width="14" height="19"/>
  </objectgroup>
 </tile>
 <tile id="12">
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="2" width="13" height="19"/>
  </objectgroup>
 </tile>
 <tile id="14" type="stand">
  <properties>
   <property name="Name" value="left_stand"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="2" width="13" height="19"/>
  </objectgroup>
 </tile>
 <tile id="13" type="left">
  <properties>
   <property name="Name" value="left_walk_left"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="3" width="13" height="19"/>
  </objectgroup>
 </tile>
 <tile id="15" type="left">
  <properties>
   <property name="Name" value="left_walk_right"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="3" width="13" height="19"/>
  </objectgroup>
 </tile>
</tileset>
