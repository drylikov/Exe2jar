/**
 * Exe2Jar - Copyright (c) 2018 - 2019 r0da [r0da@protonmail.ch]
 *
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/ or send a letter to
 * Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
 *
 * By using Exe2Jar, you agree to the above license and its terms.
 *
 *      Attribution - You must give appropriate credit, provide a link to the license and indicate if changes were
 *                    made. You must do so in any reasonable manner, but not in any way that suggests the licensor
 *                    endorses you or your use.
 *
 *   Non-Commercial - You may not use the material (Exe2Jar) for commercial purposes.
 *
 *   No-Derivatives - If you remix, transform, or build upon the material (Exe2Jar), you may not distribute the
 *                    modified material. You are, however, allowed to submit the modified works back to the original
 *                    Exe2Jar project in attempt to have it added to the original project.
 *
 * You may not apply legal terms or technological measures that legally restrict others
 * from doing anything the license permits.
 *
 * No warranties are given.
 */

package core.signature;

import core.pefile.PE;
import java.util.ArrayList;

/**
 * Classe qui verifie une collection de patern
 *
 * @author r0da
 */
public class CommunCheck implements Check {

    // Message d'entete
    private String resultMessage;

    // Liste des paterns a chercher
    private ArrayList<CommunPatern> paterns;

    public CommunCheck(ArrayList<CommunPatern> paterns, String message) {
        this.resultMessage = message;
        this.paterns = paterns;
    }

    /**
     * Initialize le message
     *
     * @param String str : message
     */
    public void setMessage(String str) {
        this.resultMessage = str;
    }

    /**
     * Check si un patern de la classe est present
     *
     * @param PE executable : executable sur le quel on test les paterns
     * @return vrai si un patern est trouvé
     */
    public boolean invoke(PE executable) {

        for (CommunPatern p : paterns) {

            if (p.isFound(executable)) {
                p.applyAspects(executable);

                System.out.println(resultMessage + p.getName() + " found");

                return true;
            }
        }

        return false;
    }
}
