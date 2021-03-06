/*
 *   Copyright (C) 2014  Alfons Wirtz
 *   website www.freerouting.net
 *
 *   Copyright (C) 2017 Michael Hoffer <info@michaelhoffer.de>
 *   Website www.freerouting.mihosoft.eu
*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License at <http://www.gnu.org/licenses/> 
 *   for more details.
 *
 * ReadScopeParameter.java
 *
 * Created on 21. Juni 2004, 08:28
 */

package eu.mihosoft.freerouting.designforms.specctra;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Default parameter type used while reading a Specctra dsn-file.
 *
 * @author Alfons Wirtz
 */
public class ReadScopeParameter
{
    
    /** Creates a new instance of ReadScopeParameter */
    ReadScopeParameter(Scanner p_scanner, eu.mihosoft.freerouting.interactive.IBoardHandling p_board_handling,
                       eu.mihosoft.freerouting.board.BoardObservers p_observers,
                       eu.mihosoft.freerouting.datastructures.IdNoGenerator p_item_id_no_generator, eu.mihosoft.freerouting.board.TestLevel p_test_level)
    {
        scanner = p_scanner;
        board_handling = p_board_handling;
        observers = p_observers;
        item_id_no_generator = p_item_id_no_generator;
        test_level = p_test_level;
    }
    
    final Scanner scanner;
    final eu.mihosoft.freerouting.interactive.IBoardHandling board_handling;
    final NetList netlist = new NetList();
    
    final eu.mihosoft.freerouting.board.BoardObservers  observers;
    final eu.mihosoft.freerouting.datastructures.IdNoGenerator item_id_no_generator;
    final eu.mihosoft.freerouting.board.TestLevel test_level;
    
    /** Collection of elements of class PlaneInfo.
     * The plane cannot be inserted directly into the boards, because the layers may not be read completely.
     */
    final Collection<PlaneInfo> plane_list = new LinkedList<PlaneInfo>();
    
    /**
     * Component placement information.
     * It is filled while reading the placement scope and can be
     * evaluated after reading the library and network scope.
     */
    final Collection<ComponentPlacement> placement_list = new LinkedList<ComponentPlacement>();
    
    
    /**
     * The names of the via padstacks filled while reading the structure scope
     * and evaluated after reading the library scope.
     */
    Collection<String> via_padstack_names = null;
    
    boolean via_at_smd_allowed = false;
    eu.mihosoft.freerouting.board.AngleRestriction snap_angle = eu.mihosoft.freerouting.board.AngleRestriction.FORTYFIVE_DEGREE;
    
    /** The logical parts are used for pin and gate swaw */
    java.util.Collection<PartLibrary.LogicalPartMapping> logical_part_mappings
            = new java.util.LinkedList<PartLibrary.LogicalPartMapping>();
    java.util.Collection<PartLibrary.LogicalPart> logical_parts = new java.util.LinkedList<PartLibrary.LogicalPart>();
    
    /** The following objects are from the parser scope. */
    String string_quote = "\"";
    String host_cad = null;
    String host_version = null;
    
    boolean dsn_file_generated_by_host = true;
    
    boolean board_outline_ok = true;
    
    final Collection<String[]> constants = new LinkedList<String[]>();
    eu.mihosoft.freerouting.board.Communication.SpecctraParserInfo.WriteResolution write_resolution = null;
    
    
    /** The following objects will be initialised when the structure scope is read. */
    CoordinateTransform coordinate_transform = null;
    LayerStructure layer_structure = null;
    eu.mihosoft.freerouting.interactive.AutorouteSettings autoroute_settings = null;
    
    eu.mihosoft.freerouting.board.Unit unit = eu.mihosoft.freerouting.board.Unit.MIL;
    int resolution = 100; // default resulution
    
    /** Information for inserting a plane */
    static class PlaneInfo
    {
        PlaneInfo(Shape.ReadAreaScopeResult p_area, String p_net_name)
        {
            area = p_area;
            net_name = p_net_name;
        }
        
        final Shape.ReadAreaScopeResult area;
        final String net_name;
    }
}
