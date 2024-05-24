<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.10.2" name="Overworld" tilewidth="16" tileheight="16" tilecount="1440" columns="40">
 <image source="../textures/zelda-esque/Overworld.png" width="640" height="576"/>
 <tile id="40">
  <animation>
   <frame tileid="40" duration="150"/>
   <frame tileid="41" duration="150"/>
   <frame tileid="42" duration="150"/>
   <frame tileid="43" duration="150"/>
  </animation>
 </tile>
 <tile id="107">
  <objectgroup draworder="index" id="2">
   <object id="2" name="bone_pile_bounding_box" type="bounding_box" x="1" y="2">
    <polyline points="0,0 0,5 1,7 1,10 3,11 3,14 9,14 15,9 15,5 7,0 0,0"/>
   </object>
  </objectgroup>
 </tile>
 <tile id="123">
  <animation>
   <frame tileid="123" duration="100"/>
   <frame tileid="124" duration="100"/>
   <frame tileid="125" duration="100"/>
  </animation>
 </tile>
 <tile id="147">
  <objectgroup draworder="index" id="2">
   <object id="1" name="skull_bounding_box" type="bounding_box" x="1" y="3" width="14" height="12">
    <ellipse/>
   </object>
  </objectgroup>
 </tile>
 <tile id="163">
  <animation>
   <frame tileid="163" duration="71"/>
   <frame tileid="164" duration="71"/>
   <frame tileid="165" duration="71"/>
  </animation>
 </tile>
 <tile id="260">
  <animation>
   <frame tileid="258" duration="100"/>
   <frame tileid="259" duration="100"/>
   <frame tileid="260" duration="100"/>
  </animation>
 </tile>
 <tile id="338">
  <animation>
   <frame tileid="338" duration="100"/>
   <frame tileid="339" duration="100"/>
   <frame tileid="340" duration="100"/>
  </animation>
 </tile>
</tileset>
