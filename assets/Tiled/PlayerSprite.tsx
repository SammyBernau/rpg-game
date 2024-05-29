<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.10.2" name="PlayerSprite" tilewidth="16" tileheight="23" tilecount="40" columns="8">
 <image source="../textures/samsRendition/humanoid/PlayerSprite.png" width="128" height="128"/>
 <tile id="0" type="front">
  <properties>
   <property name="Name" value="front_walk_right"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="2" width="14" height="19"/>
  </objectgroup>
 </tile>
 <tile id="1" type="stand">
  <properties>
   <property name="Name" value="front_stand"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="1" width="14" height="19"/>
  </objectgroup>
 </tile>
 <tile id="2" type="front">
  <properties>
   <property name="Name" value="front_walk_left"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="2" width="14" height="19"/>
  </objectgroup>
 </tile>
 <tile id="3" type="front_dodge">
  <properties>
   <property name="Name" value="front_dodge_1"/>
   <property name="Order" value="1"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="0" y="2" width="16" height="20"/>
  </objectgroup>
 </tile>
 <tile id="4" type="front_dodge">
  <properties>
   <property name="Name" value="front_dodge_2"/>
   <property name="Order" value="2"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="2" width="15" height="18"/>
  </objectgroup>
 </tile>
 <tile id="5" type="front_dodge">
  <properties>
   <property name="Name" value="front_dodge_3"/>
   <property name="Order" value="3"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="0" y="0" width="16" height="17"/>
  </objectgroup>
 </tile>
 <tile id="6" type="front_dodge">
  <properties>
   <property name="Name" value="front_dodge_4"/>
   <property name="Order" value="4"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="1" width="14" height="16"/>
  </objectgroup>
 </tile>
 <tile id="8" type="right">
  <properties>
   <property name="Name" value="right_walk_right"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="2" y="2" width="13" height="19"/>
  </objectgroup>
 </tile>
 <tile id="9" type="stand">
  <properties>
   <property name="Name" value="right_stand"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="2" y="1" width="13" height="19"/>
  </objectgroup>
 </tile>
 <tile id="10" type="right">
  <properties>
   <property name="Name" value="right_walk_left"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="2" y="2" width="13" height="19"/>
  </objectgroup>
 </tile>
 <tile id="16" type="back">
  <properties>
   <property name="Name" value="back_walk_right"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="2" width="14" height="19"/>
  </objectgroup>
 </tile>
 <tile id="17" type="stand">
  <properties>
   <property name="Name" value="back_stand"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="1" width="14" height="19"/>
  </objectgroup>
 </tile>
 <tile id="18" type="back">
  <properties>
   <property name="Name" value="back_walk_left"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="2" width="14" height="19"/>
  </objectgroup>
 </tile>
 <tile id="24" type="left">
  <properties>
   <property name="Name" value="left_walk_left"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="3" width="13" height="19"/>
  </objectgroup>
 </tile>
 <tile id="25" type="stand">
  <properties>
   <property name="Name" value="left_stand"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="2" width="13" height="19"/>
  </objectgroup>
 </tile>
 <tile id="26" type="left">
  <properties>
   <property name="Name" value="left_walk_right"/>
  </properties>
  <objectgroup draworder="index" id="2">
   <object id="1" x="1" y="3" width="13" height="19"/>
  </objectgroup>
 </tile>
</tileset>
