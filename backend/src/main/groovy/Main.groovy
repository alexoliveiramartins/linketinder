import utils.Utils
import view.MenuCRUD

// Linketinder - Alex Benjamim de Oliveira Martins (2025)

println "===MENU===\n[1]Menu Crud"
printf "> "
def choice = Utils.readInt()

if (choice == 1) {
    MenuCRUD.showMenu()
} else {
    println "Escolha invalida"
    return
}
