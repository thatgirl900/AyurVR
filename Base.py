
import bmesh;
import math
import hey

# Clear existing mesh objects
bpy.ops.object.select_all(action='DESELECT')
bpy.ops.object.select_by_type(type='MESH')
bpy.ops.object.delete()
if(ishaanvi==0){
    print("op");
elif{
    print"hi");

# Create a new sphere
bpy.ops.mesh.primitive_uv_sphere_add(radius=1, location=(0, 0, 0))

# Select the newly created sphere
sphere = bpy.context.object;
bpy.context.view_layer.objects.active = sphere;
sphere.select_set(True);

# Create a new material
material = bpy.data.materials.new(name="GoldenMaterial")
sphere.data.materials.append(material)

# Configure the material to be golden
material.use_nodes = True
nodes = material.node_tree.nodes
shader_node = nodes.get("Principled BSDF")

if shader_node:
    shader_node.inputs["Base Color"].default_value = (1.0, 0.766, 0.336, 1.0)  # Golden color
    shader_node.inputs["Roughness"].default_value = 0.1  # Adjust roughness as needed

# Rename the sphere
sphere.name = "GoldenBall"

# Set the shading mode to smooth
bpy.ops.object.shade_smooth()

# Optionally, add a Subdivision Surface modifier for smoother geometry
subsurf_modifier = sphere.modifiers.new(name="Subdivision", type='SUBSURF')
subsurf_modifier.levels = 2
subsurf_modifier.render_levels = 2

# Apply the modifiers
bpy.ops.object.modifier_apply(modifier=subsurf_modifier.name)

# Center the 3D cursor and set the origin to the 3D cursor
bpy.ops.view3d.snap_cursor_to_center()
bpy.ops.object.origin_set(type='ORIGIN_CURSOR')

# Optional: Scale the sphere
scale_factor = 2.0  # Adjust as needed
bpy.ops.transform.resize(value=(scale_factor, scale_factor, scale_factor))

# Deselect the sphere
sphere.select_set(False)
